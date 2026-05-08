package ast;

public class BinaryOpNode extends Node {
    public String operator;
    public Node left;
    public Node right;

    public BinaryOpNode(String operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
}