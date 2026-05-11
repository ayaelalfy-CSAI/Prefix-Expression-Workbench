package parser;

import java.util.List;
import lexer.Token;
import lexer.Token.Type;

public class TokenManager {

    private List<Token> tokens;
    private int pos = 0;
    public Token currentToken;

    public TokenManager(List<Token> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            throw new IllegalArgumentException("Token list cannot be null or empty");
        }
        this.tokens = tokens;
        this.currentToken = tokens.get(pos);
    }

    public void advance() {
        pos++;
        if (pos < tokens.size()) {
            currentToken = tokens.get(pos);
        } else {
            currentToken = new Token(Type.EOF, null);
        }
    }

    public void eat(Type type) {
        if (currentToken == null) {
            throw new IllegalStateException("Attempted to eat token from an empty or null stream");
        }

        if (currentToken.type == type) {
            advance();
        } else {
            throw new RuntimeException(
                "Syntax Error: Expected " + type +
                " but found " + currentToken.type + 
                (currentToken.value != null ? " ('" + currentToken.value + "')" : "") +
                " at position " + pos
            );
        }
    }

    public int getPos() {
        return pos;
    }

    public boolean isFinished() {
        return currentToken != null && currentToken.type == Type.EOF;
    }
}