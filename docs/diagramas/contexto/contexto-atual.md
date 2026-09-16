# Diagrama de contexto atual

Visão de nível de contexto (C4 - Nível 1): quem usa o MediConnect e com quais sistemas
externos ele conversa. O núcleo do sistema é a classe `ServicoAplicacaoHospital`.

```mermaid
flowchart TB
    subgraph Atores["Atores"]
        REC["Atendente / Recepção<br/><i>agenda consultas</i>"]
        MED["Médico<br/><i>solicita exames</i>"]
        ENF["Equipe de internação<br/><i>interna e acompanha</i>"]
        PAC["Paciente<br/><i>recebe notificações</i>"]
    end

    SIS["<b>MediConnect</b><br/>Sistema de gestão hospitalar<br/>[Java - ServicoAplicacaoHospital]<br/><i>agendamento, exames e internação</i>"]

    subgraph Externos["Sistemas externos (legado)"]
        PLANO["Operadora de Plano de Saúde<br/>[ApiLegadaPlanoSaude]<br/><i>autoriza procedimentos</i>"]
        LAB["Laboratório LabX<br/>[ClienteLabX]<br/><i>recebe exames e devolve resultados</i>"]
        WA["Gateway WhatsApp<br/>[ApiWhatsappHospital]<br/><i>entrega mensagens</i>"]
        MAIL["Servidor de E-mail<br/><i>hoje apenas System.out</i>"]
    end

    REC -->|"agendar(consulta)"| SIS
    MED -->|"solicitarExame(exame, custo)"| SIS
    ENF -->|"internar / listarInternacoes"| SIS

    SIS -->|"autorizarProcedimento(...)<br/>string ';' delimitada"| PLANO
    SIS -->|"enviarExame(...) / resultado(...)<br/>código HTTP numérico"| LAB
    SIS -->|"enviarMensagem(telefone, texto)"| WA
    SIS -->|"notificar(EMAIL, ...)"| MAIL

    WA -.->|"confirmação de exame"| PAC
    MAIL -.->|"confirmação de consulta"| PAC

    classDef sistema fill:#1f6feb,stroke:#0b3d91,color:#ffffff
    classDef ator fill:#e8eaf0,stroke:#6b7280,color:#111827
    classDef externo fill:#9ca3af,stroke:#4b5563,color:#111827
    class SIS sistema
    class REC,MED,ENF,PAC ator
    class PLANO,LAB,WA,MAIL externo
```

## Fronteiras e responsabilidades

| Elemento | Dentro/Fora | Responsabilidade | Risco atual |
| --- | --- | --- | --- |
| `ServicoAplicacaoHospital` | Dentro | Orquestra agendamento, exame e internação | Concentra regra de negócio **e** conhecimento de protocolo externo |
| `ApiLegadaPlanoSaude` | Fora | Autorizar procedimento | Retorna `String` `"benef;proc;OK|MANUAL"`; o parsing vaza para o serviço |
| `ClienteLabX` | Fora | Receber exame / devolver resultado | Retorna `int` 200/400; sem tratamento de indisponibilidade |
| `ApiWhatsappHospital` | Fora | Enviar mensagem | Instanciado a cada envio, dentro de `ServicoNotificacao` |
| E-mail | Fora | Enviar mensagem | Ainda não integrado — apenas impressão em console |

O diagrama deve ser atualizado se atores ou sistemas externos mudarem
(ver `docs/contexto/sistemas-externos.md` e `docs/contexto/stakeholders.md`).
