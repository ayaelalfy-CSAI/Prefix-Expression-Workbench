package lexer;

public class Token {

    public enum Type {
        LPAREN,
        RPAREN,
        NUMBER,
        IDENTIFIER,
        OPERATOR,
        KEYWORD,
        EOF
    }

    public Type type;
    public String value;

    public Token(Type type, String value) {
        if (type == null) {
            throw new IllegalArgumentException("Token type cannot be null");
        }
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        if (type == Type.EOF) {
            return "EOF";
        }
        return type + (value != null ? "(" + value + ")" : "");
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}