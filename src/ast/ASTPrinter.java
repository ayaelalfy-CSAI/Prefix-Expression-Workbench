package ast;

public class ASTPrinter {

    // =========================
    // 1. SIMPLE DEBUG PRINT
    // =========================
    public static void printSimple(Node node, String indent) {
        if (node == null) {
            System.out.println(indent + "<null node>");
            return;
        }

        if (node instanceof NumberNode) {
            System.out.println(indent +
                    "Number: " + ((NumberNode) node).value);
        }

        else if (node instanceof IdentifierNode) {
            System.out.println(indent +
                    "Identifier: " + ((IdentifierNode) node).name);
        }

        else if (node instanceof LetNode) {

            LetNode let = (LetNode) node;

            System.out.println(indent +
                    "Let: " + let.name);

            printSimple(let.value, indent + "   ");
        }

        else if (node instanceof BinaryOpNode) {

            BinaryOpNode bin = (BinaryOpNode) node;

            System.out.println(indent +
                    "Operator: " + bin.operator);

            printSimple(bin.left, indent + "   ");
            printSimple(bin.right, indent + "   ");
        }
    }

    // =========================
    // 2. TREE PRINT
    // =========================
    public static void printTree(Node node) {
        printTree(node, "", true);
    }

    private static void printTree(Node node, String prefix, boolean isLast) {
        if (node == null) {
            String connector = isLast ? "└── " : "├── ";
            System.out.println(prefix + connector + "<null>");
            return;
        }

        String connector = isLast ? "└── " : "├── ";

        if (node instanceof NumberNode) {
            System.out.println(prefix + connector +
                    ((NumberNode) node).value);
        }

        else if (node instanceof IdentifierNode) {
            System.out.println(prefix + connector +
                    ((IdentifierNode) node).name);
        }

        else if (node instanceof LetNode) {

            LetNode let = (LetNode) node;

            System.out.println(prefix + connector +
                    "let " + let.name);

            String newPrefix = prefix + (isLast ? "    " : "│   ");

            printTree(let.value, newPrefix, true);
        }

        else if (node instanceof BinaryOpNode) {

            BinaryOpNode bin = (BinaryOpNode) node;

            System.out.println(prefix + connector +
                    bin.operator);

            String newPrefix = prefix + (isLast ? "    " : "│   ");

            printTree(bin.left, newPrefix, false);
            printTree(bin.right, newPrefix, true);
        }
    }
}