# Classes - visão atual

```mermaid
classDiagram
    Main --> ServicoAplicacaoHospital
    ServicoAplicacaoHospital --> RepositorioPacientesEmMemoria
    ServicoAplicacaoHospital --> RepositorioConsultasEmMemoria
    ServicoAplicacaoHospital --> ServicoNotificacao
    ServicoAplicacaoHospital --> ApiLegadaPlanoSaude
    ServicoAplicacaoHospital --> ClienteLabX
```
