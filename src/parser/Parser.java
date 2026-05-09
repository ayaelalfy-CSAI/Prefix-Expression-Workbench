package parser;

import ast.Node;
import java.util.List;
import lexer.Token;

public class Parser {

    private TokenManager tm;
    private ExpressionParser ep;

    public Parser(List<Token> tokens) {
        this.tm = new TokenManager(tokens);
        this.ep = new ExpressionParser(tm);
    }

    public Node parse() {
        return ep.expression();
    }
}