package lexer;

public class Token {

    public enum Type {
        LPAREN,     // (
        RPAREN,     // )
        NUMBER,     // 123
        IDENTIFIER, // x, y, abc
        OPERATOR,   // + - * /
        KEYWORD,    // let , int , ...
        EOF         // نهاية ال input 
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