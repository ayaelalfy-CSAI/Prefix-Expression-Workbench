package parser;

import lexer.Token;
import lexer.Token.Type;
import java.util.List;

public class TokenManager {

    private List<Token> tokens;
    private int pos = 0;
    public Token currentToken;

    public TokenManager(List<Token> tokens) {
        this.tokens = tokens;
        this.currentToken = tokens.get(pos);
    }

    public void advance() {
        pos++;
        if (pos < tokens.size()) {
            currentToken = tokens.get(pos);
        }
    }

    public void eat(Type type) {
        if (currentToken.type == type) {
            advance();
        } else {
            throw new RuntimeException(
                "Syntax Error: Expected " + type +
                " but found " + currentToken.type
            );
        }
    }
}