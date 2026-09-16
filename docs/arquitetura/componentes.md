# Componentes do FinCore

| Componente | Responsabilidade atual | Evidência no código |
|---|---|---|
| Entrada | iniciar cenário demonstrativo | `Main.java` |
| Serviço de aplicação | abrir contas, transferir, calcular tarifa, emitir cartão e coordenar integrações | `service/ServicoAplicacaoFinCore.java` |
| Contas | armazenar contas em memória | `repository/RepositorioContasEmMemoria.java` |
| Antifraude | aprovar/rejeitar operação por regra simples | `service/ServicoAntifraude.java` |
| Notificações | escolher canal e chamar gateways legados | `service/ServicoNotificacao.java` |
| PIX legado | processar PIX no formato externo atual | `legacy/ClientePixLegado.java` |
| TED legado | processar TED no formato externo atual | `legacy/ClienteTedLegado.java` |

## Problemas preservados
O serviço de aplicação concentra responsabilidades demais; integrações externas são chamadas diretamente; regras de tarifa e emissão de cartão dependem de condicionais; objetos de domínio permanecem mutáveis e expostos.
