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

    private int operation(int a, int b, char op){
        switch(op){
            case '+': return a + b;
            case '-': return a -b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }
    public String eval(String expression){
        if(!isValid(expression)) return "ERR";
        Stack<Integer> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();

        char[] expr = expression.toCharArray();

        for(int i = 0; i < expr.length; i++){
            if(expr[i] == ' ') continue;

            else if(expr[i] >= '0' && expr[i] <= '9' ){
                StringBuilder str = new StringBuilder();
                while(i != expr.length && expr[i] >= '0' && expr[i] <= '9'){
                    str.append(expr[i]);
                    i++;
                }
                i--;
                int opr = Integer.parseInt(str.toString());
                operand.push(opr);
            }

            else if(expr[i] == '('){
                operator.push(expr[i]);
            }

            else if(expr[i] == ')'){
                while(!operator.isEmpty() && operator.peek() != '('){
                    int op2 = operand.pop();
                    int op1 = operand.pop();
                    int result =  operation(op1,op2,operator.pop());
                    operand.push(result);
                }
                operator.pop();
            }

            else{
                while(!operator.isEmpty() && precedence(operator.peek()) > precedence(expr[i])){
                    int op2 = operand.pop();
                    int op1 = operand.pop();

                    int result = operation(op1,op2,operator.pop());
                    operand.push(result);
                }
                operator.push(expr[i]);
            }
        }

        while(!operator.empty()){
            int op2 = operand.pop();
            int op1 = operand.pop();

            int result = operation(op1,op2,operator.pop());
            operand.push(result);
        }


        return operand.peek().toString();
    }


}
