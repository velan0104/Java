package JavaCalculator;
import java.util.Stack;

public class Solve {

    private boolean isValid(String expression){
        int open = 0;
        int close = 0;
        if(expression.contains("/0")){
            return false;
        }
        for (int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '('){
                open++;
            }
            if(expression.charAt(i) == ')'){
                close++;
            }
        }
        return open == close;
    }
    private int precedence(char op){
        if(op == '+' || op == '-')
            return 1;
        if(op == '*' || op == '/')
            return 2;
        return 0;
    }

    private double operation(double a, double b, char op){
        switch(op){
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;

        }
        return 0;
    }
    public String eval(String expression){
        if(!isValid(expression)) return "ERR";
        Stack<Double> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();

        char[] expr = expression.toCharArray();

        for(int i = 0; i < expr.length; i++){
            if(expr[i] == ' ') continue;

            else if((expr[i] >= '0' && expr[i] <= '9') || expr[i] == '.' ){
                StringBuilder str = new StringBuilder();
                while(i != expr.length && ((expr[i] >= '0' && expr[i] <= '9') || expr[i] == '.')){
                    str.append(expr[i]);
                    i++;
                }
                i--;
                double opr = Double.parseDouble(str.toString());

                operand.push(opr);
            }

            else if(expr[i] == '('){
                operator.push(expr[i]);
            }

            else if(expr[i] == ')'){
                while(!operator.isEmpty() && operator.peek() != '('){
                    double op2 = operand.pop();
                    double op1 = operand.pop();
                    double result =  operation(op1,op2,operator.pop());
                    operand.push(result);
                }
                operator.pop();
            }

            else{
                while(!operator.isEmpty() && precedence(operator.peek()) > precedence(expr[i])){
                    double op2 = operand.pop();
                    double op1 = operand.pop();

                    double result = operation(op1,op2,operator.pop());
                    operand.push(result);
                }
                operator.push(expr[i]);
            }
        }

        while(!operator.empty()){
            double op2 = operand.pop();
            double op1 = operand.pop();

            double result = operation(op1,op2,operator.pop());
            operand.push(result);
        }


        return operand.peek().toString();
    }


}
