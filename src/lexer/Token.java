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
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return type + (value != null ? "(" + value + ")" : "");
    }
}