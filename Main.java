import java.util.List;

import ast.BinaryOpNode;
import ast.NumberNode;
import lexer.Lexer;
import lexer.Token;
import ast.*;

public class Main{
    public static void main(String[] args){
        // /Lexer Testing
         String input = "(* (+ 1 2) 5)";

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }

        // AST Testing
        Node left = new BinaryOpNode(
                "+",
                new NumberNode(1),
                new NumberNode(2)
        );

        Node root = new BinaryOpNode(
                "*",
                left,
                new NumberNode(5)
        );

        System.out.println("AST created successfully");
    }
}