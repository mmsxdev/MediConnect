# Arquitetura inicial

A equipe anterior declarou que o FinCore utiliza "microsserviços", porém todo o código entregue está no mesmo módulo e compartilha diretamente objetos internos.

```mermaid
flowchart LR
Cliente --> FinCore
FinCore --> PIX
FinCore --> TED
FinCore --> SMS
```

Não há registro dos critérios usados para selecionar o estilo arquitetural.
