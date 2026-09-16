# Guia da documentação — MediConnect

Use este arquivo para saber **onde colocar cada coisa** no projeto.

## Regra principal

- Instruções das aulas: `docs/atividades/`
- Contexto e envolvidos: `docs/contexto/`
- Requisitos: `docs/requisitos/`
- Arquitetura e componentes: `docs/arquitetura/`
- Diagramas: `docs/diagramas/`
- Decisões de design/arquitetura: `docs/adr/`
- Evidências de execução e testes: `docs/evidencias/aula-XX/`
- Modelos para preencher: `docs/modelos/`
- Guias auxiliares: `docs/anexos/`

## Inicial e atual

Arquivos com `inicial` ou `legado` registram o ponto de partida e devem ser preservados. Atualize os arquivos com `atual` ou `atualizados` durante a evolução do projeto.

Exemplo:

```texto
docs/requisitos/requisitos-iniciais.md       -> não sobrescrever
docs/requisitos/requisitos-atualizados.md    -> atualizar quando os requisitos evoluírem

docs/diagramas/classes/classes-legado.md     -> referência inicial
docs/diagramas/classes/classes-atual.md       -> manter coerente com o código
```

## Não criar uma pasta de documentação para cada aula

Evite isto:

```texto
docs/aula-01/
docs/aula-02/
docs/aula-03/
```

A documentação deve ser organizada pelo **tipo de artefato**. Somente as evidências ficam separadas por aula.

Exemplo na Aula 05:

```texto
docs/atividades/README-Aula-05.md                 # o que fazer
docs/arquitetura/componentes.md                   # componentes identificados
docs/diagramas/componentes/componentes-atual.md   # diagrama atualizado
docs/adr/ADR-XXXX-assunto.md                       # decisão, quando necessária
docs/evidencias/aula-05/                           # evidências da atividade
```

Antes de cada atividade, abra `docs/atividades/README-Aula-XX.md` e siga os caminhos indicados.


## Escopo do 4º período

O material foi revisado para manter somente os conteúdos previstos no FO-178 desta etapa curricular. Conteúdos reservados ao período seguinte não fazem parte deste projeto-base.
