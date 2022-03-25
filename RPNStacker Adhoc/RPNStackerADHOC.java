import java.io.*;
import java.util.ArrayList;

public class RPNStackerADHOC {
    public static void main(String[] args) throws Exception {
            File input = new File(".//input//Calc1.stk"); //Mudar o input aqui
            FileReader fr = new FileReader(input);
            BufferedReader br = new BufferedReader(fr);  
            ArrayList<String> inputList = new ArrayList<String>();

            int numCount = 0, opCount = 0; //Contadores para a quantidade de números e operações na equação avaliada. numCount  = opCount + 1 no final, sempre, em casos válidos
            boolean invalidExp = false, lastEntryIsOp = false; //Booleans de condições para a equação ser válida
            String currentLine, A, B;
            while ((currentLine = br.readLine()) != null){
                int i = inputList.size() - 1;
                while (i >= 0){ //Printando a pilha atual
                    System.out.println(inputList.get(i));
                    i--;
                }
                System.out.printf("---------"); //Divisor
                if (invalidExp != true){
                    if (currentLine.matches("^\\d+$") || currentLine.matches("[\\+\\-\\*\\^]")){ //Só aceita entradas que são números INTEIROS, não-negativos, ou uma das 4 operações válidas (+, -, * e ^)
                        if(currentLine.matches("^\\d+$")){
                            lastEntryIsOp = false; //Definindo se o último elemento da equação é uma operação. Se não, a equação é inválida!
                            inputList.add(currentLine); //Push no valor atual
                            numCount++; //Definindo a quantidade de números
                            System.out.printf(" Push %s%n", currentLine); //Printando a operação realizada na etapa atual
                        }
                        else if (currentLine.matches("[\\+\\-\\*\\^]")){
                            if (inputList.size() < 2){ //Se a pilha atual tiver menos que 2 elementos, não tem como realizar operações. Logo, se sim, é uma equação inválida.
                                invalidExp = true;
                                System.out.printf("%n");
                                break;
                            }
                            B = inputList.get(inputList.size() - 1); //Pop do valor B
                            inputList.remove(inputList.size() - 1);
                            A = inputList.get(inputList.size() - 1); //Pop do valor A
                            inputList.remove(inputList.size() - 1);

                            if (A.matches("[\\+\\-\\*\\^]") || B.matches("[\\+\\-\\*\\^]")){ //Checando se qualquer um dos valores extraídos são operações. Se sim, é uma equação inválida
                                invalidExp = true;
                                System.out.printf("%n");
                                break;
                            }

                            float Afloat = Float.parseFloat(A); //Passando de String para Float
                            float Bfloat = Float.parseFloat(B);
                            String result = "INVALID"; //Valor de precaução
                            switch(currentLine){ //Operações são definidas aqui
                                case "+":
                                    result = Float.toString(Afloat + Bfloat); break;
                                case "-":
                                    result = Float.toString(Afloat - Bfloat); break;
                                case "*":
                                    result = Float.toString(Afloat * Bfloat); break;
                                case "^":
                                    result = Float.toString((float)Math.pow(Afloat, Bfloat));
                                default: break;
                            }
                            inputList.add(result); //Push no resultado
                            lastEntryIsOp = true; //Definindo se o último elemento da equação é uma operação. Se não, a equação é inválida!
                            opCount++; //Definindo a quantidade total de operações
                            System.out.printf(" Pop %s & %s, Op: %s, Push %s%n", A, B, currentLine, result); //Printando a operação realizada na etapa atual
                        }
                    }
                    else {
                        invalidExp = true; //Caso a entrada não seja uma operação válida ou número inteiro, a equação é inválida.
                        break;
                    }
                }
                else break;
            }
            if (numCount != opCount + 1 || invalidExp == true || lastEntryIsOp == false || inputList.size() != 1) //Caso o tamanho da pilha final não seja 1, ou se qualquer uma das outras condições anteriores não serem válidas, printa-se um resultado de erro.
                System.out.println("ERROR: Invalid Expression");
            else{
                System.out.println(inputList.get(0)); 
                System.out.println("---------"); //Printando a última parte da exibição da pilha
                System.out.println("Result: " + inputList.get(0)); //Printando o resultado final
            }
            br.close();
    }
}
