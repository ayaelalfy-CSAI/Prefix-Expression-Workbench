#Prefix Expression Workbench

A Java-based interpreter for evaluating prefix arithmetic expressions, inspired by LISP-like syntax.
This project demonstrates how to build a simple compiler front-end including a Lexer, Parser, AST, and Interpreter.

#Features
-Handwritten Lexer (Tokenization)
-Recursive Descent Parser
-Abstract Syntax Tree (AST) Construction
-Expression Evaluation Engine
-Support for Nested Expressions
-Variable binding using let
-Symbol Table (Environment for identifiers)
-Syntax Error Detection


#Expression Format (Prefix Notation)

All expressions follow prefix notation:
(+ 3 4)
(* 2 5)
(+ 3 (* 2 4))


#Grammar
expression →
    number
  | identifier
  | "(" operator expression expression ")"
  | "(" "let" identifier expression ")"

operator →
    "+" | "-" | "*" | "/"

Examples
➤ Basic Expression
(+ 3 4)

Result:
7

➤ Nested Expression
(+ 3 (* 2 4))

Result:
11

➤ Using Variables
(let x 5)
(+ x 3)

Result:
8


#Project Structure

Prefix-Expression-Workbench/
│
├── lexer/        # Tokenization logic
├── parser/       # Recursive descent parser
├── ast/          # AST node definitions
├── interpreter/  # Evaluation logic
├── src/          # Supporting files
│
├── Main.java     # Entry point
├── App.java      # Execution logic


#How It Works
1-Input expression
2-Lexer converts input into tokens
3-Parser builds the AST
4-Interpreter evaluates the AST
5-Final result is returned


#Workflow
Input → Lexer → Tokens → Parser → AST → Interpreter → Result


#How to Run
1. Compile
javac Main.java
2. Run
java Main
3. With the GUI
App.java


#Technologies Used
Java
swigger(for the gui)




