import ast.ASTPrinter;
import ast.Node;
import java.util.List;
import lexer.Lexer;
import lexer.Token;
import parser.Parser;

public class Main {
    public static void main(String[] args) {
        String input = "(* (+ 1 2) 5)";

        try {
            System.out.println("--- Starting Lexical Analysis ---");
            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.tokenize();

            for (Token token : tokens) {
                System.out.println(token);
            }

            System.out.println("\n--- Starting Parsing ---");
            if (tokens.isEmpty()) {
                throw new IllegalStateException("No tokens found to parse.");
            }

            Parser parser = new Parser(tokens);
            Node ast = parser.parse();

            if (ast == null) {
                throw new Exception("Parser returned a null AST.");
            }

            System.out.println("Parsing completed successfully.");
            System.out.println("AST created successfully.");

            System.out.println("\nSIMPLE AST:");
            ASTPrinter.printSimple(ast, "");

            System.out.println("\nTREE AST:");
            ASTPrinter.printTree(ast);

        } catch (IllegalArgumentException e) {
            System.err.println("[Lexer Error]: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.err.println("[Parser Error]: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[General Error]: An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); 
        } finally {
            System.out.println("\n--- Execution Finished ---");
        }
    }
}