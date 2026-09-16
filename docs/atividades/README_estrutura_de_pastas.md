# Guia da documentação do FinCore

Este arquivo indica **onde colocar cada coisa** durante as aulas. Para saber **o que fazer em cada aula**, abra `docs/atividades/README-Aula-XX.md`.

## Onde registrar cada artefato

| O que foi produzido | Onde colocar |
|---|---|
| Contexto, problema, atores e stakeholders | `docs/contexto/` |
| Requisitos e rastreabilidade | `docs/requisitos/` |
| Explicação da arquitetura e componentes | `docs/arquitetura/` |
| Diagramas | `docs/diagramas/` |
| Decisão arquitetural | `docs/adr/` |
| Evidência específica de uma aula | `docs/evidencias/aula-XX/` |
| Instruções das aulas | `docs/atividades/` |
| Modelos reutilizáveis | `docs/modelos/` |
| Guias auxiliares de Maven e JUnit | `docs/anexos/` |

## Regra de organização

Não crie pastas `aula-01`, `aula-02` etc. para contexto, requisitos, arquitetura ou diagramas. Esses documentos permanecem organizados pelo **tipo de artefato**.

Use pastas por aula somente em `docs/evidencias/`.

### Exemplo

Na Aula 05, se o grupo atualizar os componentes do FinCore:

- descrição dos componentes: `docs/arquitetura/componentes.md`;
- diagrama: `docs/diagramas/componentes/componentes-atual.md`;
- decisão relevante: próximo arquivo sequencial em `docs/adr/`;
- resultado de teste ou outra evidência: `docs/evidencias/aula-05/`.

## Estado inicial e estado atual

Os arquivos `*-inicial.md` e `*-legado.md` preservam o ponto de partida. Os arquivos `*-atual.md` ou `*-atualizados.md` devem acompanhar a evolução real do projeto.
