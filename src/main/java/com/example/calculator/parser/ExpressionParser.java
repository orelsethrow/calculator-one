package com.example.calculator.parser;

import com.example.calculator.operator.Operator;
import com.example.calculator.operator.OperatorFactory;
import java.util.*;

public class ExpressionParser {
    
    public double evaluate(String expression, OperatorFactory operatorFactory) {
        String cleanedExpression = expression.replaceAll("\\s+", "");
        
        List<String> tokens = tokenize(cleanedExpression);
        List<String> postfix = infixToPostfix(tokens, operatorFactory);
        
        return evaluatePostfix(postfix, operatorFactory);
    }
    
    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder numberBuilder = new StringBuilder();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                numberBuilder.append(c);
            } else if (c == '-' && (i == 0 || "#+*/-(".indexOf(expression.charAt(i-1)) >= 0)) {
                // 음수 처리: 첫 번째 문자이거나 연산자 다음에 오는 -는 음수로 처리
                numberBuilder.append(c);
            } else {
                if (numberBuilder.length() > 0) {
                    tokens.add(numberBuilder.toString());
                    numberBuilder.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }
        
        if (numberBuilder.length() > 0) {
            tokens.add(numberBuilder.toString());
        }
        
        return tokens;
    }
    
    private List<String> infixToPostfix(List<String> tokens, OperatorFactory operatorFactory) {
        List<String> output = new ArrayList<>();
        Stack<String> operators = new Stack<>();
        
        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else {
                Operator currentOp = operatorFactory.getOperator(token);
                
                while (!operators.isEmpty()) {
                    String topToken = operators.peek();
                    Operator topOp = operatorFactory.getOperator(topToken);
                    
                    if (topOp != null && topOp.getPrecedence() >= currentOp.getPrecedence()) {
                        output.add(operators.pop());
                    } else {
                        break;
                    }
                }
                
                operators.push(token);
            }
        }
        
        while (!operators.isEmpty()) {
            output.add(operators.pop());
        }
        
        return output;
    }
    
    private double evaluatePostfix(List<String> postfix, OperatorFactory operatorFactory) {
        Stack<Double> stack = new Stack<>();
        
        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else {
                double right = stack.pop();
                double left = stack.pop();
                
                Operator operator = operatorFactory.getOperator(token);
                double result = operator.calculate(left, right);
                stack.push(result);
            }
        }
        
        return stack.pop();
    }
    
    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}