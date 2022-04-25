import java.io.*;
import java.util.ArrayList;
import stacker.rpn.lexer.*;

public class RPNStackerADHOC {
    public static void main(String[] args) throws Exception {
        File input = new File(".//input//Calc5.stk"); //Mudar o input aqui
        FileReader fr = new FileReader(input);
        BufferedReader br = new BufferedReader(fr);  
        ArrayList<Token> tokenList = new ArrayList<Token>();
        ArrayList<Token> inputList = new ArrayList<Token>();

        boolean scanning = true; //Ativar scanning aqui

        int numCount = 0, opCount = 0; //Contadores para a quantidade de números e operações na equação avaliada. numCount  = opCount + 1 no final, sempre, em casos válidos
        boolean invalidExp = false, lastEntryIsNum = false, operatorNumCheck = false; //Booleans de condições para a equação ser válida
        String currentLine;
        while ((currentLine = br.readLine()) != null){
            if (currentLine.matches("^\\d+$") || currentLine.matches("[\\+\\-\\*]")){ //Só aceita entradas que são números INTEIROS, não-negativos, ou uma das 3 operações válidas (+, -, *)
                if (currentLine.matches("^\\d+$")){
                    numCount++;
                    tokenList.add(new Token(TokenType.NUM, currentLine));
                }
                else{
                    opCount++;
                    switch(currentLine){ //Operações são definidas aqui
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
                tokenList.add(new Token(TokenType.EOF, currentLine));
                invalidExp = true;
                break;
            }
        }
        br.close();
        if (invalidExp != true && numCount - opCount == 1){
            for (int i = 0; i < tokenList.size(); i++){
                Token currentToken = tokenList.get(i);
                if (currentToken.type == TokenType.NUM){
                    lastEntryIsNum = true;
                    inputList.add(currentToken);
                }
                else if (currentToken.type == TokenType.PLUS || currentToken.type == TokenType.MINUS || currentToken.type == TokenType.STAR){
                    lastEntryIsNum = false;
                    if (inputList.size() < 2){
                        operatorNumCheck = false;
                        break;
                    }
                    else if (inputList.get(inputList.size() - 1).type != TokenType.NUM || inputList.get(inputList.size() - 2).type != TokenType.NUM){
                        operatorNumCheck = false;
                        break;
                    }
                    else{
                        Token B = inputList.get(inputList.size() - 1);
                        inputList.remove(inputList.size() - 1);
                        Token A = inputList.get(inputList.size() - 1);
                        inputList.remove(inputList.size() - 1);
                        switch(currentToken.type){
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

        if (invalidExp == true){
            if (tokenList.get(tokenList.size() - 1).type == TokenType.EOF){
                System.out.println("Error: Unexpected character: " + tokenList.get(tokenList.size() - 1).lexeme);
            }
            else
                System.out.println("Error: Invalid expression");
        }
        else if (operatorNumCheck == true){
            System.out.println("Error: Operator doesn't have enough inputs");
        }
        else if (lastEntryIsNum == true || numCount - opCount > 1){
            System.out.println("Error: Number without Operator");
        }
        else if (numCount - opCount < 1){
            System.out.println("Error: Operator without Number");
        }
        else
            System.out.println(inputList.get(0).lexeme);
        
        if(scanning == true && invalidExp != true){
            System.out.println("");
            for (int i = 0; i < tokenList.size(); i++)
            System.out.println(tokenList.get(i).toString());
        }
    }
}
