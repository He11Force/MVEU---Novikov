package ru.novikov;

import java.util.Scanner;
import java.util.Stack;

public class SimpleCalculator {
    public static double result;
    public static int positionDelete = 0;
    public static String expression;
    public static String tempNumber;
    public static String tempExpression;
    public static String position;
    public static int positionInt;
    public static int minpos = 99;
    public static int maxpos = 0;
    public static double sin = 10;
    public static double cos = 10;
    public static double tan = 10;

    public static void main(String a[]) {
        System.out.println("Simple calculator: 1");
        System.out.println("With sin/cos/tg: 2");
        System.out.println("Choise calculator:");
        Scanner calc = new Scanner(System.in);
        int calctype = Integer.parseInt(calc.nextLine());
        if(calctype == 2){
            Scanner in = new Scanner(System.in);
            System.out.print("Input var follow by number(+-*/)s/c/t(number)(+-*/)number: ");
            expression = in.nextLine();
            deattachingChars();
        }else{
            Scanner in = new Scanner(System.in);
            System.out.print("Input var: ");
            expression = in.nextLine();
        }
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
        System.out.println(expression);
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == 's') {
                positionDelete = i;
                StringBuffer sb = new StringBuffer();
                StringBuffer pos = new StringBuffer();

                for (int j = i + 1; expression.charAt(j) != '+' && expression.charAt(j) != '-' && expression.charAt(j) != '*' && expression.charAt(j) != '/'; j++) {
                    sb.append(expression.charAt(j));
                    pos.append(j-1);
                }

                position = pos.toString();
                positionInt = Integer.parseInt(position);
                tempNumber = sb.toString();
                sin = Math.sin(Math.toRadians(Double.parseDouble(tempNumber)));
                System.out.println(sin);
                System.out.println(expression + " behind");
                expression = expression.replace("s", "");
            }else if (expression.charAt(i) == 'c') {
                positionDelete = i;
                StringBuffer sb = new StringBuffer();
                StringBuffer pos = new StringBuffer();

                for (int j = i + 1; expression.charAt(j) != '+' && expression.charAt(j) != '-' && expression.charAt(j) != '*' && expression.charAt(j) != '/'; j++) {
                    sb.append(expression.charAt(j));
                    pos.append(j-1);
                }

                position = pos.toString();
                positionInt = Integer.parseInt(position);
                tempNumber = sb.toString();
                System.out.println(tempNumber);
                if(tempNumber.equals("90")){
                    cos = 0;
                }else{
                    cos = Math.cos(Math.toRadians(Double.parseDouble(tempNumber)));
                }
                System.out.println(cos);
                System.out.println(expression + " behind");
                expression = expression.replace("c", "");
            }else if (expression.charAt(i) == 't') {
                positionDelete = i;
                StringBuffer sb = new StringBuffer();
                StringBuffer pos = new StringBuffer();

                for (int j = i + 1; expression.charAt(j) != '+' && expression.charAt(j) != '-' && expression.charAt(j) != '*' && expression.charAt(j) != '/'; j++) {
                    sb.append(expression.charAt(j));
                    pos.append(j-1);
                }

                position = pos.toString();
                positionInt = Integer.parseInt(position);
                tempNumber = sb.toString();
                tan = Math.tan(Math.toRadians(Double.parseDouble(tempNumber)));
                System.out.println(tan);
                System.out.println(expression + " behind");
                expression = expression.replace("t", "");
            }




        }
        // - es
        System.out.println(expression + " after");
        System.out.println(position + " position");


        for (int j = 0; j < position.length(); j++ ) {
            if(minpos > position.charAt(j)){
                switch (position.charAt(j)) {
                    case 48 -> minpos = 0;
                    case 49 -> minpos = 1;
                    case 50 -> minpos = 2;
                    case 51 -> minpos = 3;
                    case 52 -> minpos = 4;
                    case 53 -> minpos = 5;
                    case 54 -> minpos = 6;
                    case 55 -> minpos = 7;
                    case 56 -> minpos = 8;
                    case 57 -> minpos = 9;
                }
            } else if(maxpos < position.charAt(j)){
                switch (position.charAt(j)) {
                    case 48 -> maxpos = 0;
                    case 49 -> maxpos = 1;
                    case 50 -> maxpos = 2;
                    case 51 -> maxpos = 3;
                    case 52 -> maxpos = 4;
                    case 53 -> maxpos = 5;
                    case 54 -> maxpos = 6;
                    case 55 -> maxpos = 7;
                    case 56 -> maxpos = 8;
                    case 57 -> maxpos = 9;
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer(expression);
        stringBuffer.delete(minpos,maxpos+1);
        expression = stringBuffer.toString();
        System.out.println(minpos +" "+ maxpos + "min, max");
        System.out.println(expression + " after delete");

        //setter cos

        StringBuffer last = new StringBuffer(expression);
        if(sin != 10) {
            for (int i = 0; i < expression.length(); i++){
                if((expression.charAt(i) == '+' && expression.charAt(i+1) == '+')||(expression.charAt(i) == '+' && expression.charAt(i+1) == '-')
                        ||(expression.charAt(i) == '-' && expression.charAt(i+1) == '+')||(expression.charAt(i) == '-' && expression.charAt(i+1) == '-')
                        ||(expression.charAt(i) == '*' && expression.charAt(i+1) == '*')||(expression.charAt(i) == '*' && expression.charAt(i+1) == '/')
                        ||(expression.charAt(i) == '/' && expression.charAt(i+1) == '*')||(expression.charAt(i) == '/' && expression.charAt(i+1) == '/')
                        ||(expression.charAt(i) == '+' && expression.charAt(i+1) == '*')||(expression.charAt(i) == '-' && expression.charAt(i+1) == '*')
                        ||(expression.charAt(i) == '*' && expression.charAt(i+1) == '-')||(expression.charAt(i) == '*' && expression.charAt(i+1) == '+')
                        ||(expression.charAt(i) == '+' && expression.charAt(i+1) == '/')||(expression.charAt(i) == '-' && expression.charAt(i+1) == '/')
                        ||(expression.charAt(i) == '/' && expression.charAt(i+1) == '-')||(expression.charAt(i) == '/' && expression.charAt(i+1) == '+')){
                    last.insert(i+1,sin);
                }
            }
        }else if(cos != 10){
            for (int i = 0; i < expression.length(); i++){
                if((expression.charAt(i) == '+' && expression.charAt(i+1) == '+')||(expression.charAt(i) == '+' && expression.charAt(i+1) == '-')
                        ||(expression.charAt(i) == '-' && expression.charAt(i+1) == '+')||(expression.charAt(i) == '-' && expression.charAt(i+1) == '-')
                        ||(expression.charAt(i) == '*' && expression.charAt(i+1) == '*')||(expression.charAt(i) == '*' && expression.charAt(i+1) == '/')
                        ||(expression.charAt(i) == '/' && expression.charAt(i+1) == '*')||(expression.charAt(i) == '/' && expression.charAt(i+1) == '/')
                        ||(expression.charAt(i) == '+' && expression.charAt(i+1) == '*')||(expression.charAt(i) == '-' && expression.charAt(i+1) == '*')
                        ||(expression.charAt(i) == '*' && expression.charAt(i+1) == '-')||(expression.charAt(i) == '*' && expression.charAt(i+1) == '+')
                        ||(expression.charAt(i) == '+' && expression.charAt(i+1) == '/')||(expression.charAt(i) == '-' && expression.charAt(i+1) == '/')
                        ||(expression.charAt(i) == '/' && expression.charAt(i+1) == '-')||(expression.charAt(i) == '/' && expression.charAt(i+1) == '+')){
                    last.insert(i+1,cos);
                }
            }
        }else if(tan != 10) {
            for (int i = 0; i < expression.length(); i++){
                if((expression.charAt(i) == '+' && expression.charAt(i+1) == '+')||(expression.charAt(i) == '+' && expression.charAt(i+1) == '-')
                        ||(expression.charAt(i) == '-' && expression.charAt(i+1) == '+')||(expression.charAt(i) == '-' && expression.charAt(i+1) == '-')
                        ||(expression.charAt(i) == '*' && expression.charAt(i+1) == '*')||(expression.charAt(i) == '*' && expression.charAt(i+1) == '/')
                        ||(expression.charAt(i) == '/' && expression.charAt(i+1) == '*')||(expression.charAt(i) == '/' && expression.charAt(i+1) == '/')
                        ||(expression.charAt(i) == '+' && expression.charAt(i+1) == '*')||(expression.charAt(i) == '-' && expression.charAt(i+1) == '*')
                        ||(expression.charAt(i) == '*' && expression.charAt(i+1) == '-')||(expression.charAt(i) == '*' && expression.charAt(i+1) == '+')
                        ||(expression.charAt(i) == '+' && expression.charAt(i+1) == '/')||(expression.charAt(i) == '-' && expression.charAt(i+1) == '/')
                        ||(expression.charAt(i) == '/' && expression.charAt(i+1) == '-')||(expression.charAt(i) == '/' && expression.charAt(i+1) == '+')){
                    last.insert(i+1,tan);
                }
            }
        }

        expression = last.toString();
        System.out.println(expression + " LAST");
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
                if (rpn.charAt(i) == '*')stack.push(b*a);
                if (rpn.charAt(i) == '/')stack.push(b/a);

            }
        }
        return stack.pop();
    }

    private int getPriority (char token) {
        if (token == '*' || token == '/') return 3;
        if (token == '+' || token == '-') return 2;
        if (token == '(') return 1;
        if (token == ')') return -1;
        else return 0;
    }
}
