package ast;

public class LetNode extends Node {
    public String name;
    public Node value;

    public LetNode(String name, Node value) {
        if (name == null || value == null) {
            throw new IllegalArgumentException("LetNode must have a valid name and value node.");
        }
        this.name = name;
        this.value = value;
    }
}