# Anexo 03 — Modelo de teste JUnit 5

Substitua:

- `br.edu.projeto.servico` pelo pacote real;
- `MinhaClasse` pelo nome real da classe;
- `meuMetodo()` pelo comportamento que será testado.

```java
package br.edu.projeto.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MinhaClasseTest {

    @Test
    void deveExecutarComResultadoEsperado() {

        // 1. Preparar
        MinhaClasse objeto = new MinhaClasse();

        // 2. Executar
        var resultado = objeto.meuMetodo();

        // 3. Verificar
        assertEquals(/* esperado */, resultado);
    }
}
```

O arquivo deve ser salvo em uma estrutura correspondente ao pacote.

Exemplo:

```texto
package br.edu.projeto.servico;
```

deve estar em:

```texto
src/test/java/br/edu/projeto/servico/MinhaClasseTest.java
```
