# MediConnect - Projeto do Aluno - 4º Período

Projeto didático evolutivo da Unidade Curricular **Design de Software**.

O estado inicial contém **problemas intencionais de design, arquitetura, qualidade, integração e documentação**. Esses problemas não devem ser corrigidos todos de uma vez. Cada equipe deve evoluir o mesmo projeto conforme a atividade indicada em `docs/atividades/README-Aula-XX.md`.

## Escopo curricular

Este projeto foi preparado para o **4º período**. Não contém conteúdos de projeto de software reservados à etapa curricular posterior. Quando a disciplina mencionar **estilos ou padrões arquiteturais**, o tratamento deve permanecer no nível de organização da arquitetura previsto no FO-178, sem antecipar conteúdos do 5º período.

## Execução

```bash
mvn clean test
mvn exec:java -Dexec.mainClass=br.edu.mediconnect.Main
```

Também é possível compilar o código principal com `javac`, conforme os anexos em `docs/anexos`.

## Regra didática

- Leia somente a atividade da aula atual.
- Preserve o domínio MediConnect.
- Registre decisões e evidências nas pastas indicadas.
- Não tente transformar o sistema inteiro de uma vez.
- Toda alteração deve ser justificável pelo conteúdo efetivamente estudado até aquela aula.
