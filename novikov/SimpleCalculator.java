package ru.novikov;

import java.util.Scanner;
import java.util.Stack;

public class SimpleCalculator {
    public static double result;
    public static int positionDelete = 0;
    public static String expression;
    public static String tempNumber;
    public static String tempExpression;

    public static void main(String a[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input: ");
        expression = in.nextLine();
        deattachingChars();
        result = new SimpleCalculator().decide(expression);
        //Получение данных с класса RoundedCalculator
        RoundedCalculator roundedcalculator = new RoundedCalculator();
        String formattedDouble;
        formattedDouble = roundedcalculator.sendformatteddouble();
        // Вывод
        System.out.println("Result: "+ formattedDouble);
        System.out.println("Full result: "+ result);



    }
    public static void deattachingChars() {
        for (int i = 0;i < expression.length(); i++){
            if(expression.charAt(i) == 's'){
                positionDelete = i;
                StringBuffer sb = new StringBuffer();
                for (int j = i+1; expression.charAt(j) != '+' && expression.charAt(j) != '-'  && expression.charAt(j) != '*' && expression.charAt(j) != '/' && expression.charAt(j) != ' ' && j+1 != expression.length(); j++){
                    sb.append(expression.charAt(j));
                    System.out.println("deatach ++" + j + " - i,j - " + i + " ,expr - " + expression.charAt(j));
                }
                tempNumber = sb.toString();
                System.out.println(Math.sin(Math.toRadians(Double.parseDouble(tempNumber))));
            }
        }
        System.out.println(removeCharAt(expression, positionDelete));
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public double decide (String expression) {
        String prerared = preparingExpression(expression);
        String rpn = expressionToRPN(prerared);
        return rpnToAnswer(rpn);
    }

    private String preparingExpression (String expression) {
        String preparingExpression = new String();

        for(int token = 0; token < expression.length(); token++){
            char symbol = expression.charAt(token);
            if (symbol == '-') {
                if (token == 0) preparingExpression+='0';
                else if(expression.charAt(token-1) == '(') preparingExpression+='0';
            }
            preparingExpression+=symbol;
        }
        return preparingExpression;
    }

    private String expressionToRPN (String expression) {
        String current = "";
        Stack<Character> stack = new Stack<>();

        int priority;
        for (int i = 0; i < expression.length(); i++) {
            priority = getPriority(expression.charAt(i));
            if (priority == 0) current+=expression.charAt(i);
            if (priority == 1) stack.push(expression.charAt(i));
            // For operand's
            if (priority > 1) {
                current+= " ";
                while (!stack.empty()){
                    if (getPriority(stack.peek()) >= priority) {
                        current+=stack.pop();
                    }
                    else break;
                }
                //System.out.println(expression.charAt(i) + " ETRPN");
                stack.push(expression.charAt(i));
            }
            // For ")"
            if (priority == -1) {
                current+=" ";
                while (getPriority(stack.peek()) !=1 ){
                    current+= stack.pop();
                }
                stack.pop();
            }
            System.out.println(current + "                                    Debug");
        }
        //
        while (!stack.empty()) {
            current+= stack.pop();
        }
        return current;

    }

    private double rpnToAnswer (String rpn) {
        String operand;
        Stack<Double> stack = new Stack<>();

        for(int i = 0; i < rpn.length(); i++) {
            if(rpn.charAt(i) == ' ') continue;
            if(getPriority(rpn.charAt(i)) == 0){
                operand = new String();
                while (rpn.charAt(i) != ' ' && getPriority(rpn.charAt(i)) == 0){
                    operand+= rpn.charAt(i++);
                    if (i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(operand));
            }

            if (getPriority(rpn.charAt(i)) > 1) {
                double a = stack.pop(), b = stack.pop();
                if (rpn.charAt(i) == '+')stack.push(b+a);
                if (rpn.charAt(i) == '-')stack.push(b-a);
                if (rpn.charAt(i) == '*' || rpn.charAt(i) == 'x')stack.push(b*a);
                if (rpn.charAt(i) == '/')stack.push(b/a);

            }
        }
        return stack.pop();
    }

    private int getPriority (char token) {
        if (token == '*' ||  token == 'x' || token == '/') return 3;
        if (token == '+' || token == '-') return 2;
        if (token == '(') return 1;
        if (token == ')') return -1;
        else return 0;
    }
}
