package algorithms;

import java.util.List;
import java.util.Stack;

public class PostfixEvaluator {

    public double evaluate(List<String> postfixTokens) {
        Stack<Double> stack = new Stack<>();

        for (String token : postfixTokens) {
            
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } 
           
            else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid Expression");
                }
                
               
                double val2 = stack.pop(); 
                double val1 = stack.pop();

                switch (token) {
                    case "+": stack.push(val1 + val2); break;
                    case "-": stack.push(val1 - val2); break;
                    case "*": stack.push(val1 * val2); break;
                    case "/": 
                        if (val2 == 0) throw new ArithmeticException("Division by Zero");
                        stack.push(val1 / val2); 
                        break;
                    case "^": stack.push(Math.pow(val1, val2)); break;
                    default: throw new IllegalArgumentException("Unknown Operator: " + token);
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid Expression Syntax");
        }

        return stack.pop();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}