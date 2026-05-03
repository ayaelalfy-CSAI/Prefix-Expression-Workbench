package lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private String input;
    private int pos = 0;
    private char currentChar;

    public Lexer(String input) {
        this.input = input;
        this.currentChar = input.charAt(0);
    }

    private void advance() {
        pos++;
        if (pos < input.length()) {
            currentChar = input.charAt(pos);
        } else {
            currentChar = '\0'; // نهاية النص
        }
    }

    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance();
        }
    }

    private String readNumber() {
        StringBuilder result = new StringBuilder();

        while (currentChar != '\0' && Character.isDigit(currentChar)) {
            result.append(currentChar);
            advance();
        }
        return result.toString();
    }

    private String readIdentifier() {
        StringBuilder result = new StringBuilder();

        while (currentChar != '\0' &&
                (Character.isLetterOrDigit(currentChar))) {
            result.append(currentChar);
            advance();
        }
        return result.toString();
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (currentChar != '\0') {

            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }

            if (currentChar == '(') {
                tokens.add(new Token(Token.Type.LPAREN, "("));
                advance();
                continue;
            }

            if (currentChar == ')') {
                tokens.add(new Token(Token.Type.RPAREN, ")"));
                advance();
                continue;
            }

            // أرقام
            if (Character.isDigit(currentChar)) {
                String num = readNumber();
                tokens.add(new Token(Token.Type.NUMBER, num));
                continue;
            }

            // كلمات أو identifiers
            if (Character.isLetter(currentChar)) {
                String id = readIdentifier();

                // check keywords
                if (id.equals("let")) {
                    tokens.add(new Token(Token.Type.KEYWORD, id));
                }
                // operators
                else if (id.equals("+") || id.equals("-")
                        || id.equals("*") || id.equals("/")) {
                    tokens.add(new Token(Token.Type.OPERATOR, id));
                }
                else {
                    tokens.add(new Token(Token.Type.IDENTIFIER, id));
                }
                continue;
            }

            // operators مثل + - * /
            if ("+-*/".indexOf(currentChar) != -1) {
                char op = currentChar;
                tokens.add(new Token(Token.Type.OPERATOR, String.valueOf(op)));
                advance();
                continue;
            }

            throw new RuntimeException("Invalid character: " + currentChar);
        }

        tokens.add(new Token(Token.Type.EOF, null));
        return tokens;
    }
}