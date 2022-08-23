import java.io.*;
import java.nio.file.*;
import java.util.*;

class RPNCompiler
{
    public static void main(String args[])
    {
        Stack<Integer> postFixStack = new Stack<Integer>();
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get("Calc1.stk"))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Integer lineInt = Integer.parseInt(line);
                    postFixStack.push(lineInt);
                    sb.append(line).append("\n");
                }
                catch(NumberFormatException e) {
                    int opTop = postFixStack.pop();
                    int opBottom = postFixStack.pop();
                    int result = 0;
                    switch(line) {
                        case "*":
                            result = opBottom * opTop;
                            break;
                        case "/":
                            result = opBottom / opTop;
                            break;
                        case "+":
                            result = opBottom + opTop;
                            break;
                        case "-":
                            result = opBottom - opTop;
                            break;
                    }
                    postFixStack.push(result);
                }
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        System.out.println(postFixStack.pop());
    }
}