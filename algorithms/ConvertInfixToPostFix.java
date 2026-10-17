package algorithms;

import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class ConvertInfixToPostFix {
	
	private Stack<String> operatorStack;
    private List<String> postfixList;

    public ConvertInfixToPostFix() {
        this.operatorStack = new Stack<>();
        this.postfixList = new ArrayList<>();
	

}
    
    private int precedence(String op) {
        switch (op) {
            case "^": return 3;
            case "*":
            case "/": return 2;
            case "+":
            case "-": return 1;
            default: return -1;
        }
    }
    
 //  μέθοδος για να βλέπουμε αν ένας χαρακτήρας είναι τελεστής
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' ||  c == '(';
    }
    
   
    
    public List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder operand = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            
            if (ch == ' ') continue;

            // ελεγχω αρνητικο προσημο
            if (ch == '-') {
                
                boolean isNegativeSign = false;
                
                if (i == 0) { //πρώτος χαρακτήρας 
                    isNegativeSign = true;
                } else {
                    
                    char prev = expression.charAt(i - 1);
                    if (isOperator(prev)) { //αν ο προηγουμενος χαρακτηρας ειναι τελεστης
                        isNegativeSign = true;
                    }
                }

                if (isNegativeSign) {
                    operand.append(ch); 
                    continue;           
                }
            }

            
            if (Character.isDigit(ch) || ch == '.') {
                operand.append(ch);
            } else {
            	
                if (operand.length() > 0) {
                    tokens.add(operand.toString());
                    operand = new StringBuilder();
                }
                
                tokens.add(Character.toString(ch));
            }
        }
        
         
        if (operand.length() > 0) {
            tokens.add(operand.toString());
        }
        
        return tokens;
    }
    
    
    public List<String> convert(String expression) {
        operatorStack.clear();
        postfixList.clear();
        
        List<String> infixTokens = tokenize(expression);

        for (String elem : infixTokens) {
            
            if (elem.equals("(")) {
                operatorStack.push(elem);
            } 
            else if (elem.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    postfixList.add(operatorStack.pop());
                }
                if (!operatorStack.isEmpty()) operatorStack.pop(); 
            } 
            else if (elem.equals("+") || elem.equals("-") || elem.equals("*") || elem.equals("/") || elem.equals("^")) {
                while (!operatorStack.isEmpty() && precedence(elem) <= precedence(operatorStack.peek())) {
                    postfixList.add(operatorStack.pop());
                }
                operatorStack.push(elem);
            } 
            else {
                postfixList.add(elem);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixList.add(operatorStack.pop());
        }

        return  postfixList;
    }
}
    
    
