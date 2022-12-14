import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.lang.*;

class RPNCompiler
{
    public Integer compile(String filename, Boolean scanMode)
    {
        ArrayList<Token> tokenList = new ArrayList<Token>();
        Stack<Integer> postFixStack = new Stack<Integer>();
        StringBuilder sb = new StringBuilder();
        Regex regex = new Regex();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Token token = regex.getToken(line);
                tokenList.add(token);
                if (scanMode) {
                    System.out.println(token);
                }
            }  
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        for (Token token : tokenList) {
            Integer value = 0;
            if (token.type == TokenType.NUM) {
                value = Integer.parseInt(token.lexeme);
            }
            else {
                Integer topValue = postFixStack.pop();
                Integer bottomValue = postFixStack.pop();
                
                if (token.type == TokenType.MINUS) {
                    value = bottomValue - topValue;
                }
                else if (token.type == TokenType.PLUS) {
                    value = bottomValue + topValue;
                }
                else if (token.type == TokenType.SLASH) {
                    value = bottomValue / topValue;
                }
                else if (token.type == TokenType.STAR) {
                    value = bottomValue * topValue;
                }
            }
            postFixStack.push(value);
        }

        Integer result = postFixStack.pop();
        return result;

    }
}