package ast;

public class LetNode extends Node {
    public String name;
    public Node value;

    public LetNode(String name, Node value) {
        this.name = name;
        this.value = value;
    }
}