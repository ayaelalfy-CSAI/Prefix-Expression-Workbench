package ast;

public class BinaryOpNode extends Node {
    public String operator;
    public Node left;
    public Node right;

    public BinaryOpNode(String operator, Node left, Node right) {
        if (operator == null || left == null || right == null) {
            throw new IllegalArgumentException("BinaryOpNode cannot have null operator, left, or right child.");
        }
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
}