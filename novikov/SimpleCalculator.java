package ru.novikov;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class SimpleCalculator {
    public static double result;

    public static void main(String a[]) {
        Scanner in = new Scanner(System.in);
        System.out.print("Input: ");
        String expression = in.nextLine();
        //String expression = "-(2+2)";
        result = new SimpleCalculator().decide(expression);

        RoundedCalculator roundedcalculator = new RoundedCalculator();
        String formattedDouble = null;
        formattedDouble = roundedcalculator.sendformatteddouble();

        System.out.println("Result: "+ formattedDouble);
        System.out.println("Full result: "+ result);

        /*try(FileInputStream fin=new FileInputStream("C://Diers//notes.txt")) {
            int i=-1;
            while((i=fin.read())!=-1){
                System.out.print((char)i);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }*/

        try(FileOutputStream fos=new FileOutputStream("C://notes.txt")) {
            byte[] buffer = expression.getBytes();
            fos.write(buffer, 0, buffer.length);
            byte[] buffer3 = formattedDouble.getBytes();
            fos.write(buffer3, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

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

                if (rpn.charAt(i) == 's')stack.push(Math.sin(Math.toRadians(a)));
            }
        }
        return stack.pop();
    }

    private int getPriority (char token) {
        if (token == 's' || token == 'c' || token == 't') return 4;
        if (token == '*' ||  token == 'x' || token == '/') return 3;
        if (token == '+' || token == '-') return 2;
        if (token == '(') return 1;
        if (token == ')') return -1;
        else return 0;
    }
}
