package ast;

public class ASTPrinter {

    // =========================
    // PRETTY TREE PRINT (ASCII)
    // =========================
    public static void print(Node node) {
        print(node, "", true);
    }

    private static void print(Node node,
                              String prefix,
                              boolean isLast) {

        if (node == null) {

            System.out.println(
                    prefix +
                    (isLast ? "\\-- " : "|-- ") +
                    "null"
            );

            return;
        }

        String connector =
                isLast ? "\\-- " : "|-- ";

        // =========================
        // NumberNode
        // =========================
        if (node instanceof NumberNode) {

            NumberNode num =
                    (NumberNode) node;

            System.out.println(
                    prefix +
                    connector +
                    num.value
            );
        }

        // =========================
        // IdentifierNode
        // =========================
        else if (node instanceof IdentifierNode) {

            IdentifierNode id =
                    (IdentifierNode) node;

            System.out.println(
                    prefix +
                    connector +
                    id.name
            );
        }

        // =========================
        // LetNode
        // =========================
        else if (node instanceof LetNode) {

            LetNode let =
                    (LetNode) node;

            System.out.println(
                    prefix +
                    connector +
                    "let " + let.name
            );

            String childPrefix =
                    prefix +
                    (isLast ? "    " : "|   ");

            print(let.value,
                    childPrefix,
                    true);
        }

        // =========================
        // BinaryOpNode
        // =========================
        else if (node instanceof BinaryOpNode) {

            BinaryOpNode bin =
                    (BinaryOpNode) node;

            System.out.println(
                    prefix +
                    connector +
                    bin.operator
            );

            String childPrefix =
                    prefix +
                    (isLast ? "    " : "|   ");

            // left
            print(bin.left,
                    childPrefix,
                    false);

            // right
            print(bin.right,
                    childPrefix,
                    true);
        }
    }
}