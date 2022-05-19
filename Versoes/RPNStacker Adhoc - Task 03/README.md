# RPNStacker Adhoc

Task 03 da cadeira de Compiladores 2021.2, realizada por Gabriel Ferreira Rocha

## Mudanças

Com o projeto Postfix, apenas houve a mudança de adicionar a habilidade de se colocar variáveis estáticas no código. Comparado a Task 02, a principal mudança é que o projeto agora aceita inputs diretamente do console, ao invés de necessitar ser lido de um arquivo.

As variáveis aceitas são apenas números naturais, **não aceitando outra variável como valor** e há uma mensagem de erro diferente para quando o erro está localizado dentro de uma variável.

## Variáveis Iniciais

O projeto já é carregado com três variáveis iniciais:

x, com valor "+"
y, com valor "10"
z, com valor "y"

Assim, qualquer equação com a variável x deve resultar no erro "[Lex] Error: Unexpected character in variable: "+" in variable "x"", e qualquer equação com a variável "[Lex] Error: Unexpected character in variable: "y" in variable "z"".

Uma variável não-iniciada dará um simples erro de "Unexpected character".

##Exemplo de Entradas

<img src="https://i.imgur.com/MTg530Z.png">