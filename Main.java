import ast.Node;
import java.util.List;
import lexer.Lexer;
import lexer.Token;
import parser.Parser;
import ast.ASTPrinter;

public class Main{
    public static void main(String[] args){
        // /Lexer Testing
        String input = "(* (+ 1 2) 5)";
        // String input = "(+ 3)"; //invalid case

        Lexer lexer = new Lexer(input);
        List<Token> tokens = lexer.tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }

        // PARSER
        Parser parser = new Parser(tokens);

        Node ast = parser.parse();

        System.out.println("\nParsing completed successfully");

        System.out.println("AST created successfully");

        
        System.out.println("\nSIMPLE AST:");
        ASTPrinter.printSimple(ast, "");

        System.out.println("\nTREE AST:");
        ASTPrinter.printTree(ast);
    
    

        // // AST Testing
        // Node left = new BinaryOpNode(
        //         "+",
        //         new NumberNode(1),
        //         new NumberNode(2)
        // );

        // Node root = new BinaryOpNode(
        //         "*",
        //         left,
        //         new NumberNode(5)
        // );

        // System.out.println("AST created successfully");
    }
}