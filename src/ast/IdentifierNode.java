package ast;

public class IdentifierNode extends Node {
    public String name;

    public IdentifierNode(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Identifier name cannot be null or empty.");
        }
        this.name = name;
    }
}