package parser;

import ast.*;
import lexer.Token;
import lexer.Token.Type;

public class ExpressionParser {

    private TokenManager tm;

    public ExpressionParser(TokenManager tm) {
        this.tm = tm;
    }

    public Node expression() {

        Token current = tm.currentToken;

        // NUMBER
        if (current.type == Type.NUMBER) {
            int value = Integer.parseInt(current.value);
            tm.eat(Type.NUMBER);
            return new NumberNode(value);
        }

        // IDENTIFIER
        if (current.type == Type.IDENTIFIER) {
            String name = current.value;
            tm.eat(Type.IDENTIFIER);
            return new IdentifierNode(name);
        }

        // PREFIX
        if (current.type == Type.LPAREN) {

            tm.eat(Type.LPAREN);

            // let
            if (tm.currentToken.type == Type.KEYWORD &&
                tm.currentToken.value.equals("let")) {

                tm.eat(Type.KEYWORD);

                String name = tm.currentToken.value;
                tm.eat(Type.IDENTIFIER);

                Node value = expression();

                tm.eat(Type.RPAREN);

                return new LetNode(name, value);
            }

            // operator
            String op = tm.currentToken.value;
            tm.eat(Type.OPERATOR);

            Node left = expression();
            Node right = expression();

            tm.eat(Type.RPAREN);

            return new BinaryOpNode(op, left, right);
        }

        throw new RuntimeException(
            "Unexpected token: " + current.type
        );
    }
}