# Sistemas externos

O MediConnect possui integrações simplificadas com sistemas legados:

- operadora de saúde: `ApiLegadaPlanoSaude`;
- laboratório: `ClienteLabX`;
- mensageria hospitalar: `ApiWhatsappHospital`.

No estado inicial, parte dessas dependências é conhecida diretamente pelos serviços da aplicação. Esse acoplamento é uma evidência para análise nas aulas de arquitetura, comunicação, qualidade e integração.
