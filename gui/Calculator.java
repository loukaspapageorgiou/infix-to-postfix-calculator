package gui;

import algorithms.ConvertInfixToPostFix;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import algorithms.PostfixEvaluator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {
	
	private static JTextField screen; //gia na einai prosbasimo apo to MyButton




public Calculator() {
    this.setTitle("ECE_Y325 Calculator (Infix -> Postfix)");
    this.setSize(400, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    
    createCalculatorPanel();
    
    this.setVisible(true);
}

private void createCalculatorPanel() {
    
    screen = new JTextField();
    screen.setFont(new Font("Arial", Font.BOLD, 24));
    screen.setHorizontalAlignment(JTextField.RIGHT);
    screen.setEditable(false); 
    screen.setBackground(Color.WHITE);
    
    
    JPanel buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridLayout(5, 4, 5, 5)); 
    
    String[] buttons = {
        "C", "(", ")", "/",
        "7", "8", "9", "*",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", " ", "=" 
    };

    for (String text : buttons) {
        Color bg = Color.LIGHT_GRAY;
        Color fg = Color.BLACK;
        
        
        if (text.matches("[/\\*\\-\\+=]")) {
            bg = new Color(255, 153, 51); 
            fg = Color.WHITE;
        } else if (text.equals("C")) {
            bg = Color.RED;
            fg = Color.WHITE;
        } else if (text.equals("(") || text.equals(")")) {
            bg = Color.GRAY;
            fg = Color.WHITE;
        }

        
        if (!text.equals(" ")) {  
             MyButton btn = new MyButton(text, fg, bg);
             buttonsPanel.add(btn);
        } else {
             buttonsPanel.add(new JPanel()); 
        }
    }

    this.setLayout(new BorderLayout());
    this.add(screen, BorderLayout.NORTH);
    this.add(buttonsPanel, BorderLayout.CENTER);
}

public static void appendToTextField(String text) {
    screen.setText(screen.getText() + text);
}

public static void clearTextField() {
    screen.setText("");
}

public static void initiateConversion() {
    String infix = screen.getText();
    if (infix.isEmpty()) return;

    ConvertInfixToPostFix converter = new ConvertInfixToPostFix();
    PostfixEvaluator evaluator = new PostfixEvaluator();

    try {
    	
        List<String> postfixList = converter.convert(infix);
        
        
        double result = evaluator.evaluate(postfixList);
        
       
        screen.setText(String.format("%.3f", result));
        
    } catch (Exception e) {
        screen.setText("Syntax Error");
        System.err.println("Error: " + e.getMessage());
    }
}
}


