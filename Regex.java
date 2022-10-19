public class Regex {
    String NUM_REGEX = "(\\d)+";

    String OP_REGEX = "(\\+|-|\\*|/)";
    String PLUS_OP_REGEX = "(\\+)";
    String MINUS_OP_REGEX = "(\\-)";
    String DIV_OP_REGEX = "(/)";
    String MULT_OP_REGEX = "(\\*)";

    public boolean isNum(String token_str) {
        return token_str.matches(NUM_REGEX);
    }

    public boolean isPlus(String token_str) {
        return token_str.matches(PLUS_OP_REGEX);
    }

    public boolean isMinus(String token_str) {
        return token_str.matches(MINUS_OP_REGEX);
    }

    public boolean isMult(String token_str) {
        return token_str.matches(MULT_OP_REGEX);
    }

    public boolean isDiv(String token_str) {
        return token_str.matches(DIV_OP_REGEX);
    }

    public TokenType getTokenType(String token_str) {
        if (isNum(token_str)) {
            return TokenType.NUM;
        }
        if (isPlus(token_str)) {
            return TokenType.PLUS;
        }
        if (isMinus(token_str)) {
            return TokenType.MINUS;
        }
        if(isMult(token_str)) {
            return TokenType.STAR;
        }
        if(isDiv(token_str)) {
            return TokenType.SLASH;
        }
        throw new Error("Unexpected character: " + token_str);
    }

    public Token getToken(String token_str) {
        TokenType tokenType = getTokenType(token_str);
        return new Token(tokenType, token_str);

    }
}
