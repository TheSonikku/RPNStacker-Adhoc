# RPNStacker Adhoc

Task 03 da cadeira de Compiladores 2021.2, realizada por Gabriel Ferreira Rocha

## Mudanças

Com o projeto Postfix, apenas houve a mudança de adicionar a habilidade de se colocar variáveis estáticas no código. Comparado a Task 02, a principal mudança é que o projeto agora aceita inputs diretamente do console, ao invés de necessitar ser lido de um arquivo, além de agora aceitar as variáveis estáticas, de acordo com o que foi pedido pela Task 03.

As variáveis aceitas são apenas números naturais, **não aceitando outra variável como valor**, somente podendo ser iniciadas com palavras que possuem apenas as letras a-z e letras A-Z, e há uma mensagem de erro diferente para quando o erro está localizado dentro de uma variável.

## Variáveis Iniciais

O projeto já é carregado com três variáveis iniciais:

- **x**, com valor "+"
- **y**, com valor "10"
- **z**, com valor "y"

Assim, qualquer equação com a variável **x** deve resultar no erro

- "[Interpreter] Error: Variable "x" has non-number value: "+"", 

qualquer equação com a variável **z** deve resultar no erro

- "[Interpreter] Error: Variable "z" has non-number value: "y"",

e uma equação com uma variável não-iniciada, como "u", deve resultar no erro

- "[Interpreter] Error: Non-initiated variable: u".

## Exemplo de Entradas

<img src="https://i.imgur.com/wf3Aao9.png">
