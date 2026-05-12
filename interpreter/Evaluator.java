package interpreter;

import ast.*;

public class Evaluator {

    private Environment env;

    public Evaluator(Environment env) {
        this.env = env;
    }

    public int eval(Node node) {

        if (node instanceof NumberNode) {
            return ((NumberNode) node).value;
        }

        if (node instanceof IdentifierNode) {
            String name = ((IdentifierNode) node).name;
            return env.get(name);
        }

        if (node instanceof BinaryOpNode) {

            BinaryOpNode bin = (BinaryOpNode) node;

            int left = eval(bin.left);
            int right = eval(bin.right);

            switch (bin.operator) {
                case "+": return left + right;
                case "-": return left - right;
                case "*": return left * right;
                case "/":
                    if (right == 0)
                        throw new RuntimeException("Division by zero");
                    return left / right;
                default:
                    throw new RuntimeException("Unknown operator: " + bin.operator);
            }
        }

        if (node instanceof LetNode) {
            LetNode let = (LetNode) node;

            int value = eval(let.value);
            env.set(let.name, value);

            return value;
        }

        throw new RuntimeException("Unknown node type");
    }
}