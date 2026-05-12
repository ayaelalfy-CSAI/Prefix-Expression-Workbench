import ast.ASTPrinter;
import ast.Node;

import java.util.List;
import java.util.Scanner;

import lexer.Lexer;
import lexer.Token;
import parser.Parser;

import interpreter.Environment;
import interpreter.Evaluator;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Environment env = new Environment();
        Evaluator evaluator = new Evaluator(env);

        System.out.println("Type expressions or 'exit' to quit\n");

        while (true) {

            try {
                System.out.print("> ");
                String input = sc.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }

                if (input.trim().isEmpty()) {
                    continue;
                }

                // =========================
                // LEXER
                // =========================
                Lexer lexer = new Lexer(input);
                List<Token> tokens = lexer.tokenize();

                System.out.println("\nTokens:");
                for (Token t : tokens) {
                    System.out.println(t);
                }

                // =========================
                // PARSER
                // =========================
                Parser parser = new Parser(tokens);
                Node ast = parser.parse();

                System.out.println("\nAST:");
                ASTPrinter.print(ast);

                // =========================
                // EVALUATION
                // =========================
                int result = evaluator.eval(ast);

                System.out.println("\nResult: " + result);
                System.out.println("-------------------------");

            }

            catch (Exception e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }

        sc.close();
    }
}