import ast.ASTPrinter;
import ast.Node;
import interpreter.Environment;
import interpreter.Evaluator;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import javax.swing.*;
import lexer.Lexer;
import lexer.Token;
import parser.Parser;

public class App {
    private JFrame frame;
    private JTextField inputField;
    private JTextArea tokenArea;
    private JTextArea astArea;
    private JLabel resultLabel;
    private Evaluator evaluator;

    public App() {
        evaluator = new Evaluator(new Environment());
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Prefix Expression Workbench");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JButton runButton = new JButton("Run / Evaluate");
        
        topPanel.add(new JLabel("Enter Prefix Expression:"), BorderLayout.NORTH);
        topPanel.add(inputField, BorderLayout.CENTER);
        topPanel.add(runButton, BorderLayout.EAST);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        tokenArea = new JTextArea();
        tokenArea.setEditable(false);
        tokenArea.setBorder(BorderFactory.createTitledBorder("Tokens List"));

        astArea = new JTextArea();
        astArea.setEditable(false);
        astArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        astArea.setBorder(BorderFactory.createTitledBorder("AST Visual Tree"));

        centerPanel.add(new JScrollPane(tokenArea));
        centerPanel.add(new JScrollPane(astArea));

        JPanel bottomPanel = new JPanel(new BorderLayout());
        resultLabel = new JLabel("Result: -", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        resultLabel.setPreferredSize(new Dimension(800, 600 / 6));
        bottomPanel.add(resultLabel, BorderLayout.CENTER);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        runButton.addActionListener(e -> processInput());
        inputField.addActionListener(e -> processInput());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void processInput() {
        String input = inputField.getText().trim();
        if (input.isEmpty()) return;

        try {
            // 1. Lexer
            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.tokenize();
            
            StringBuilder tokenOutput = new StringBuilder();
            for (Token t : tokens) tokenOutput.append(t).append("\n");
            tokenArea.setText(tokenOutput.toString());

            // 2. Parser
            Parser parser = new Parser(tokens);
            Node ast = parser.parse();


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos);
            PrintStream oldOut = System.out;
            System.setOut(ps);
            
            ASTPrinter.print(ast); 
            
            System.out.flush();
            System.setOut(oldOut);
            astArea.setText(baos.toString());


            int result = evaluator.eval(ast);
            resultLabel.setText("Result: " + result);
            resultLabel.setForeground(new Color(0, 128, 0));

        } catch (Exception ex) {
            tokenArea.setText("");
            astArea.setText("");
            resultLabel.setText("Error: See Popup");
            resultLabel.setForeground(Color.RED);
            
            JOptionPane.showMessageDialog(frame, 
                "Parsing/Execution Error:\n" + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}


// (+ (* 2 3) (/ 10 (let x 2)))