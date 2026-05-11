package parser;

import ast.*;
import lexer.Token;
import lexer.Token.Type;

public class ExpressionParser {

    private TokenManager tm;

    public ExpressionParser(TokenManager tm) {
        if (tm == null) {
            throw new IllegalArgumentException("TokenManager cannot be null");
        }
        this.tm = tm;
    }

    public Node expression() {
        if (tm.currentToken == null) {
            throw new IllegalStateException("Unexpected end of input: No tokens available to parse");
        }

        Token current = tm.currentToken;

        // NUMBER
        if (current.type == Type.NUMBER) {
            try {
                int value = Integer.parseInt(current.value);
                tm.eat(Type.NUMBER);
                return new NumberNode(value);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid number format: " + current.value);
            }
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

                if (tm.currentToken.type != Type.IDENTIFIER) {
                    throw new RuntimeException("Expected identifier after 'let', but found: " + tm.currentToken.type);
                }

                String name = tm.currentToken.value;
                tm.eat(Type.IDENTIFIER);

                Node value = expression();

                if (tm.currentToken.type != Type.RPAREN) {
                    throw new RuntimeException("Expected ')' after 'let' expression, but found: " + tm.currentToken.type);
                }

                tm.eat(Type.RPAREN);

                return new LetNode(name, value);
            }

            // operator
            if (tm.currentToken.type != Type.OPERATOR) {
                throw new RuntimeException("Expected operator after '(', but found: " + tm.currentToken.type);
            }

            String op = tm.currentToken.value;
            tm.eat(Type.OPERATOR);

            Node left = expression();
            Node right = expression();

            if (tm.currentToken.type != Type.RPAREN) {
                throw new RuntimeException("Expected ')' after binary operation, but found: " + tm.currentToken.type);
            }

            tm.eat(Type.RPAREN);

            return new BinaryOpNode(op, left, right);
        }

        throw new RuntimeException(
            "Parsing Error: Unexpected token '" + current.value + "' of type " + current.type
        );
    }
}