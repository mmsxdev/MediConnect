# Sequência - solicitação de exame

Fluxo do método `ServicoAplicacaoHospital.solicitarExame(SolicitacaoExame, double)`.
É o cenário mais completo do sistema: passa por persistência, integração com a operadora,
integração com o laboratório e notificação ao paciente.

```mermaid
sequenceDiagram
    autonumber
    actor Medico as Médico
    participant App as ServicoAplicacaoHospital
    participant Pac as RepositorioPacientesEmMemoria
    participant Plano as ApiLegadaPlanoSaude
    participant Lab as ClienteLabX
    participant Notify as ServicoNotificacao
    participant WA as ApiWhatsappHospital

    Medico->>App: solicitarExame(exame, custoEstimado)
    App->>Pac: buscar(exame.idPaciente)
    Pac-->>App: paciente ou null

    alt paciente não encontrado
        App-->>Medico: false
    else paciente encontrado
        App->>Plano: autorizarProcedimento(paciente.id, codigoExame, custoEstimado)
        Plano-->>App: resposta textual terminada em OK ou MANUAL

        Note over App,Plano: O serviço faz parsing do formato da API<br/>autorizado = autorizacao.endsWith("OK")

        alt autorizado (sufixo "OK")
            App->>Lab: enviarExame(paciente.id, codigoExame)
            Lab-->>App: codigo 200 ou 400
            alt código 200
                App->>App: exame.status = "SENT_TO_LAB"
            else código 400
                App->>App: exame.status = "LAB_ERROR"
            end
        else não autorizado ("MANUAL")
            App->>App: exame.status = "DENIED"
            Note right of App: "MANUAL" (análise manual) é<br/>achatado em "DENIED" — estado perdido
        end

        App->>Notify: notificar("WHATSAPP", paciente.telefone, "Exame " + id + " = " + status)
        Notify->>WA: new ApiWhatsappHospital().enviarMensagem(telefone, texto)
        WA-->>Notify: void
        Notify-->>App: void

        App-->>Medico: exame.autorizado
    end
```

## Observações do fluxo

1. **A solicitação nunca é persistida.** Não existe repositório de `SolicitacaoExame`; o objeto
   é mutado em memória e descartado ao fim da chamada.
2. **Retorno ambíguo:** `false` significa tanto "paciente inexistente" quanto "não autorizado".
   Quem chama não consegue distinguir os casos.
3. **`LAB_ERROR` retorna `true`,** pois o retorno é `exam.autorizado` e não o status final —
   o exame falhou no laboratório mas a operação é reportada como bem-sucedida.
4. **Sem tratamento de falha de integração:** qualquer exceção de `Plano` ou `Lab` sobe sem
   retry, timeout ou fallback.
5. **Notificação é síncrona e acoplada** ao fluxo de negócio; falha de envio derruba a operação.
