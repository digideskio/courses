package trees;

import java.util.Scanner;
import java.util.Stack;

public class ExpressionTree<T> extends BinaryTree<T> {
 
    public ExpressionTree<T> makeExpressionTree(String postfix) {
        final Stack<T> nodes = new Stack<T>();
        for(int i = 0; i < postfix.length(); i++) {
            char current = postfix.charAt(i);
            if (isOperator(current)) {
                
            }
        }
    }
    
    private boolean isOperator(char current) {
        return (current == '+') || (current == '-') || (current == '*') ||
                (current == '/');
    }
 
    public static void main (String[] args) {
 
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a postfix arithmetic expression: ");
        String postfix = input.nextLine();
 
    }
}