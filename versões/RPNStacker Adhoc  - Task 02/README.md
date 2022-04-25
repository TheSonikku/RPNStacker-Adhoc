# RPNStacker Adhoc

Task 02 da cadeira de Compiladores 2021.2, realizada por Gabriel Ferreira Rocha

## Mudanças

Foi removido o print da pilha cada vez que uma operação é realizada, apenas printando o valor final. Também foi removido o operador Potência (^), tornando esse operador um caractere desconhecido na análise léxica. Além disso, se tem uma nova opção de _Scanning_ que permite se ver os _Tokens_ individualmente no fim do output.

## Operações Suportadas

Apenas números inteiros positivos são permitidos no input, pegando os valores <img src ="https://latex.codecogs.com/svg.image?A"> e <img src ="https://latex.codecogs.com/svg.image?B"> do topo do stack atual, com as operações:

**(+) Soma:** <img src="https://latex.codecogs.com/svg.image?A%20&plus;%20B">

**(-) Subtração:** <img src="https://latex.codecogs.com/svg.image?A%20-%20B">

**(*) Multiplicação:** <img src="https://latex.codecogs.com/svg.image?A%20%5Ccdot%20B">

## Como Utilizar:

Colocando o(s) arquivo(s) com a(s) equação/equações na pasta input, basta mudar o valor ```<arquivo>``` na linha

```File input = new File(".//input//<arquivo>");```

do arquivo ```RPNStackerADHOC.java``` para o nome do arquivo que possui a equação desejada. Por _default_, os arquivos tem a extensão ```.stk```.

Ademais, para ativar a opção _Scanning_, basta mudar o boolean scanning para ```true```. Caso contrário, mudar para ```false```.
  
Após isso, basta compilar e executar o código. A saída terá um formato como o seguinte:

Para entrada com caractere inválido, sem scanning:

<img src ="https://i.imgur.com/ghOgy9o.png">

Para entrada válida, com scanning:

<img src = "https://i.imgur.com/maFwwqA.png">
  
## Inputs Predefinidos:

Foram colocados 5 arquivos iniciais para demonstrar o código. Seus nomes e equações respectivas são:

**Calc1.stk:** (Resultado = 36)

4

8

\+

3

\*

**Calc2.stk:** (Resultado = 20)

10
  
10
  
\+ 
  
**Calc3.stk** (Resultado = 54)
  
4
  
5
  
\+
  
6
  
\*

**Calc4.stk** (Resultado = 17)
  
2
  
3
  
\*
  
5
  
4
  
\*
  
\+
  
9
  
\-

**Calc5.stk** (Resultado = Error: Unexpected character: ^)
  
4
  
5
  
\+
  
2
  
\*
  
10
  
3
  
\-
  
\+
  
18
  
\-
  
2
  
\^
