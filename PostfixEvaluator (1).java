package data_structures;

import java.util.Stack;

public class PostfixEvaluator {

    // Method to evaluate a postfix expression
    public static int evaluatePostfix(String expression) {
        // Create a stack to store operands
        Stack<Integer> stack = new Stack<>();
        // Split the expression into tokens
        String[] tokens = expression.split(" ");

        // Loop through each token in the expression
        for (String token : tokens) {
            // If the token is a numeric value, push it onto the stack
            if (isNumeric(token)) {
                stack.push(Integer.parseInt(token));
            } 
            // If the token is an operator, perform the operation on operands from the stack
            else if (token.equals("+")) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                stack.push(operand1 + operand2);
            } else if (token.equals("-")) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                stack.push(operand1 - operand2);
            } else if (token.equals("*")) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                stack.push(operand1 * operand2);
            } else if (token.equals("/")) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                stack.push(operand1 / operand2);
            } else if (token.equals("%")) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                stack.push(operand1 % operand2);
            } else if (token.equals("$")) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                stack.push((int) Math.pow(operand1, operand2));
            } 
            // If the token is unary operator ~, negate the top element on the stack
            else if (token.equals("~")) {
                int operand = stack.pop();
                stack.push(-operand);
            }
        }

        // The result of the expression is the top element on the stack
        return stack.pop();
    }

    // Method to check if a string is numeric
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    // Main method to test the evaluator
    public static void main(String[] args) {
        // Example postfix expressions
        String[] expressions = {
                "9 2 1 + / 4 *",
                "1 2 + 3 - 2 1 + 3 $ -",
                "1 2 3 + * 3 2 1 - + *",
                "18 6 / 20 25 - +",
                "7 ~ 5 3 + -",
                "7 5 3 - - ~ ~ ~"
        };

        // Evaluate and print the result for each expression
        for (String exp : expressions) {
            System.out.println("Expression: " + exp);
            System.out.println("Result: " + evaluatePostfix(exp));
            System.out.println();
        }
    }
}

