package parser;

import ast.Node;
import java.util.List;
import lexer.Token;

public class Parser {

    private TokenManager tm;
    private ExpressionParser ep;

    public Parser(List<Token> tokens) {
        if (tokens == null || tokens.isEmpty()) {
            throw new IllegalArgumentException("Token list cannot be null or empty");
        }
        this.tm = new TokenManager(tokens);
        this.ep = new ExpressionParser(tm);
    }

    public Node parse() {
        Node result = ep.expression();
        
        if (tm.currentToken != null && tm.currentToken.type != Token.Type.EOF) {
            throw new RuntimeException("Unexpected token after expression: " + tm.currentToken.type + " (" + tm.currentToken.value + ")");
        }
        
        return result;
    }
}