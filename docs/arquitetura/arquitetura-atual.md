# Arquitetura atual do FinCore

## Fluxo principal
`Main` -> `ServicoAplicacaoFinCore` -> repositório/serviços/integrações legadas -> saída textual/notificação.

## Estado propositalmente imperfeito
- `ServicoAplicacaoFinCore` concentra abertura de conta, transferência, tarifa, emissão de cartão e coordenação externa.
- PIX e TED ainda são chamados diretamente pelo serviço principal.
- `GatewayTransferenciaExterna` existe como contrato inicial, mas ainda não governa as integrações reais.
- dados de conta e transação continuam públicos e mutáveis.
- o serviço expõe repositório e colaboradores internos por getters.
- o débito local ocorre antes da confirmação externa e não há compensação automática em falha.

Esses pontos são intencionais para permitir diagnóstico e evolução nas aulas.
