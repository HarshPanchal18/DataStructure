
/*
Step 1: We will iterate throughout the String length. For each character, there are three cases to consider :
    1. If the Current character is a Operand.
    2. If the character is a Open or Close Parentheses.
    3. If the character is an Operator.
Step 2: If the Character at each iteration is a Operand. We simply push it into the operand or Postfix stack.
Step 3: If the character encountered is : '(' , i.e. Opening Parentheses, we push it into Operator Stack.
Step 4: Now, if we encounter ')' i.e. Closing Parenthesis, we are going to pop the elements out of Operator Stack until we get the opening '('. For each operator we pop its two operands and process them.
Step 5: The process for each operator is : We pop two elements from Postfix or operand Stack we concatenate them in reverse order with its operator and add the result again to Postfix stack for future evaluation until we get the total Postfix expression.
Step 6: Now if we get an Operator as the current character, we check whether the precedence of current operator is lower than the operator present at top of the stack. If the condition is true, we pop the operator present at the top and process its operands following Step 5. Then we push the current operator into the stack.
Step 7: At last after traversing the whole string if still we are left with any operators we pop them and continue Step 5 until the Operator Stack is empty. At last, the Postfix stack will have only one element which will be our resultant Postfix Expression.
*/

import java.util.*;

public class Infix2Postfix {

    // Returns the precedence of the given character
    public static int precedence(char currentChar) {
        if (currentChar == '+' || currentChar == '-')
            return 1;
        else if (currentChar == '*' || currentChar == '/')
            return 2; // higher precedence
        return 0;
    }

    public static String convertToPostfix(String expression) {
        Stack<Character> operators = new Stack<>();
        Stack<String> postFix = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (currentChar == '(')
                operators.push(currentChar);

            else if ((currentChar >= 'a' && currentChar <= 'z') || (currentChar >= 'A' && currentChar <= 'Z'))
                postFix.push(currentChar + "");

            else if (currentChar == ')') {
                // pop operators from the stack and append to the postFix stack
                // until an opening parenthesis is found.
                while (operators.peek() != '(') {
                    char operator = operators.pop();
                    String firstOperand = postFix.pop();
                    String secondOperand = postFix.pop();
                    String newPostFix = secondOperand + firstOperand + operator;
                    postFix.push(newPostFix);
                }
                operators.pop(); // pop '(' from stack
            }
            // If currentChar is an operator, pop operators from the stack and append to the
            // postFix stack
            else if (currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') {
                while (operators.size() > 0
                        && operators.peek() != '('
                        && precedence(currentChar) <= precedence(operators.peek())) {
                    char operator = operators.pop();
                    String firstOperand = postFix.pop();
                    String secondOperand = postFix.pop();
                    String newPostFix = secondOperand + firstOperand + operator;
                    postFix.push(newPostFix);
                }
                operators.push(currentChar);
            }
        }

        // Pop remaining operators from the stack and append them to the postFix stack
        while (operators.size() > 0) {
            char operator = operators.pop();
            String firstOperand = postFix.pop();
            String secondOperand = postFix.pop();
            String newPostFix = secondOperand + firstOperand + operator;
            postFix.push(newPostFix);
        }
        return postFix.pop(); // return result
    }

    public static void main(String[] a) {
        String infixExpression = "A*(B-C)/D+E"; // ABC-*D/E+
        System.out.println("The Infix Expression is: " + infixExpression);

        String result = convertToPostfix(infixExpression);
        System.out.println("The Postfix of the given Infix Expression is: " + result);
    }
}
