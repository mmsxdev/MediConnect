# Classes - visão atual

Diagrama coerente com o código em `src/main/java/br/edu/mediconnect`.
Classe central: **`ServicoAplicacaoHospital`** (camada de serviço / fachada de aplicação).

```mermaid
classDiagram
    direction LR

    class Main {
        +main(args)$ void
    }

    class ServicoAplicacaoHospital {
        +RepositorioPacientesEmMemoria pacientes
        +RepositorioConsultasEmMemoria consultas
        -ServicoNotificacao notificacoes
        -ApiLegadaPlanoSaude apiPlanoSaude
        -ClienteLabX clienteLab
        -List~Internacao~ internacoes
        +ServicoAplicacaoHospital(pacientes, consultas)
        +agendar(Consulta) boolean
        +solicitarExame(SolicitacaoExame, double) boolean
        +internar(String, String, String) Internacao
        +listarInternacoes() List~Internacao~
    }

    class ServicoNotificacao {
        +notificar(String canal, String destino, String texto) void
    }

    class RepositorioPacientesEmMemoria {
        -Map~String,Paciente~ dados
        +salvar(Paciente) void
        +buscar(String id) Paciente
    }

    class RepositorioConsultasEmMemoria {
        -Map~String,Consulta~ dados
        +salvar(Consulta) void
        +buscar(String id) Consulta
        +listarTodos() Collection~Consulta~
    }

    class Paciente {
        +String id
        +String nome
        +String planoSaude
        +String telefone
        +String email
    }

    class Medico {
        +String id
        +String nome
        +String especialidade
    }

    class Consulta {
        +String id
        +String idPaciente
        +String idMedico
        +String dataHora
        +String tipo
        +String status
        +String prioridade
    }

    class SolicitacaoExame {
        +String id
        +String idPaciente
        +String codigoExame
        +String status
        +boolean autorizado
    }

    class Internacao {
        +String id
        +String idPaciente
        +String quarto
        +String status
    }

    class ApiLegadaPlanoSaude {
        <<legado>>
        +autorizarProcedimento(String, String, double) String
    }

    class ClienteLabX {
        <<legado>>
        +enviarExame(String, String) int
        +resultado(String protocol) String
    }

    class ApiWhatsappHospital {
        <<legado>>
        +enviarMensagem(String telefone, String texto) void
    }

    Main ..> ServicoAplicacaoHospital : usa
    Main ..> RepositorioPacientesEmMemoria : cria
    Main ..> RepositorioConsultasEmMemoria : cria

    ServicoAplicacaoHospital o-- RepositorioPacientesEmMemoria : injetado
    ServicoAplicacaoHospital o-- RepositorioConsultasEmMemoria : injetado
    ServicoAplicacaoHospital *-- ServicoNotificacao : new (acoplamento forte)
    ServicoAplicacaoHospital *-- ApiLegadaPlanoSaude : new (acoplamento forte)
    ServicoAplicacaoHospital *-- ClienteLabX : new (acoplamento forte)
    ServicoAplicacaoHospital *-- "0..*" Internacao : internacoes

    ServicoNotificacao ..> ApiWhatsappHospital : instancia por chamada

    RepositorioPacientesEmMemoria "1" o-- "0..*" Paciente
    RepositorioConsultasEmMemoria "1" o-- "0..*" Consulta

    Consulta ..> Paciente : idPaciente (referência textual)
    Consulta ..> Medico : idMedico (referência textual)
    SolicitacaoExame ..> Paciente : idPaciente (referência textual)
    Internacao ..> Paciente : idPaciente (referência textual)
```

## Leitura do diagrama (pontos de design relevantes)

| Observação | Onde aparece |
| --- | --- |
| Repositórios são **classes concretas**, não interfaces — `ServicoAplicacaoHospital` depende de implementação. | `ServicoAplicacaoHospital:19-20` |
| `ServicoNotificacao`, `ApiLegadaPlanoSaude` e `ClienteLabX` são instanciados com `new` dentro do serviço (composição rígida, não injetável, difícil de testar). | `ServicoAplicacaoHospital:22-24` |
| `Medico` existe no modelo mas **não é usado** por nenhum repositório nem validado no agendamento. | `modelo/Medico.java` |
| `Internacao` é guardada em `List` interna, sem repositório próprio — assimetria em relação a `Paciente`/`Consulta`. | `ServicoAplicacaoHospital:25` |
| Associações entre entidades são feitas por `String` de id, sem navegabilidade nem integridade referencial. | `modelo/*.java` |
| `status` e `prioridade` são `String` livre em vez de enum — estados como `MANUAL` do plano se perdem. | `Consulta`, `SolicitacaoExame`, `Internacao` |
