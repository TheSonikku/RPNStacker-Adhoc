import java.io.*;
import java.util.ArrayList;
import stacker.rpn.lexer.*;

public class RPNStackerADHOC {
    public static void main(String[] args) throws Exception {
        File input = new File(".//input//Calc1.stk"); //Mudar o input aqui
        FileReader fr = new FileReader(input);
        BufferedReader br = new BufferedReader(fr);  
        ArrayList<Token> tokenList = new ArrayList<Token>(); //Lista de Tokens da entrada
        ArrayList<Token> inputList = new ArrayList<Token>(); //Pilha de Tokens

        boolean scanning = true; //Ativar scanning aqui!

        int numCount = 0, opCount = 0; //Contadores para a quantidade de números e operações na equação avaliada. numCount  = opCount + 1 no final, sempre, em casos válidos
        boolean invalidExp = false, lastEntryIsNum = false, operatorNumCheck = false; //Booleans de condições para a equação ser válida
        String currentLine;
        while ((currentLine = br.readLine()) != null){
            if (currentLine.matches("^\\d+$") || currentLine.matches("[\\+\\-\\*]")){ //Só aceita entradas que são números INTEIROS, não-negativos, ou uma das 3 operações válidas (+, -, *)
                if (currentLine.matches("^\\d+$")){
                    numCount++;
                    tokenList.add(new Token(TokenType.NUM, currentLine)); //Se for número, simplesmente adicionamos como novo Token de número
                }
                else{
                    opCount++;
                    switch(currentLine){ //Operações são definidas aqui e convertidas para o Token
                        case "+":
                            tokenList.add(new Token(TokenType.PLUS, currentLine)); break;
                        case "-":
                            tokenList.add(new Token(TokenType.MINUS, currentLine)); break;
                        case "*":
                            tokenList.add(new Token(TokenType.STAR, currentLine)); break;
                        default: tokenList.add(new Token(TokenType.EOF, currentLine)); break;
                    }
                }
            }
            else{
                tokenList.add(new Token(TokenType.EOF, currentLine)); //Adicionando um valor EOF pra lista se for inválido
                invalidExp = true;
                break;
            }
        }
        br.close();
        if (invalidExp != true && numCount - opCount == 1){ //Se numCount - opCount != 1, a expressão é desbalanceada e inválida.
            for (int i = 0; i < tokenList.size(); i++){
                Token currentToken = tokenList.get(i); //Pegando o próximo Token da entrada
                if (currentToken.type == TokenType.NUM){ //Se for um número, simplesmente adiciona-se ele na pilha
                    lastEntryIsNum = true; //Se a última entrada for número, não é uma expressão válida
                    inputList.add(currentToken);
                }
                else if (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS || currentToken.type == TokenType.STAR){ //Caso seja um operador, fazemos alguns checks pelo tipo e garantir que a expressão é válida
                    lastEntryIsNum = false; //Se a última entrada for operador, pode ser válido
                    if (inputList.size() < 2){ //Se a lista de inputs atual for menor que 2, não temos entradas suficientes para o operador
                        operatorNumCheck = false;
                        break;
                    }
                    else if (inputList.get(inputList.size() - 1).type != TokenType.NUM || inputList.get(inputList.size() - 2).type != TokenType.NUM){ //Se os dois últimos valores não forem números, não é possível realizar uma operação
                        operatorNumCheck = false;
                        break;
                    }
                    else{
                        Token B = inputList.get(inputList.size() - 1); //Pegando os 2 últimos tokens da pilha
                        inputList.remove(inputList.size() - 1);
                        Token A = inputList.get(inputList.size() - 1);
                        inputList.remove(inputList.size() - 1);
                        switch(currentToken.type){ //Caso switch para cada operação, colocando de volta na pilha o valor com a operação realizada
                            case PLUS: inputList.add(new Token(TokenType.NUM, Float.toString(Float.parseFloat(A.lexeme) + Float.parseFloat(B.lexeme)))); break;
                            case MINUS: inputList.add(new Token(TokenType.NUM, Float.toString(Float.parseFloat(A.lexeme) - Float.parseFloat(B.lexeme)))); break;
                            case STAR: inputList.add(new Token(TokenType.NUM, Float.toString(Float.parseFloat(A.lexeme) * Float.parseFloat(B.lexeme)))); break;
                            default: break;
                        }
                    }
                }
                else{
                    invalidExp = true;
                    break;
                }
            }
        }

        if (invalidExp == true){ //Checando os erros
            if (tokenList.get(tokenList.size() - 1).type == TokenType.EOF){
                System.out.println("Error: Unexpected character: " + tokenList.get(tokenList.size() - 1).lexeme); //Erro de caractere inválido
            }
            else
                System.out.println("Error: Invalid expression"); //Erro geral
        }
        else if (operatorNumCheck == true){
            System.out.println("Error: Operator doesn't have enough inputs"); //Erro de operador não achar entradas suficientes
        }
        else if (lastEntryIsNum == true || numCount - opCount > 1){
            System.out.println("Error: Number without Operator"); //Erro de números faltando no final da pilha
        }
        else if (numCount - opCount < 1){
            System.out.println("Error: Operator without Number"); //Erro de ter mais operadores que entradas para eles
        }
        else
            System.out.println(inputList.get(0).lexeme); //Saída correta
        
        if(scanning == true){ //Print final para o scanning caso esteja ativado
            System.out.println("");
            for (int i = 0; i < tokenList.size(); i++)
            System.out.println(tokenList.get(i).toString());
        }
    }
}
