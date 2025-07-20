package com.example.calculator.operator;

import java.util.HashMap;
import java.util.Map;

public class OperatorFactory {
    private final Map<String, Operator> operators = new HashMap<>();
    
    public OperatorFactory(Operator... operators) {
        for (Operator operator : operators) {
            this.operators.put(operator.getSymbol(), operator);
        }
    }
    
    public Operator getOperator(String symbol) {
        return operators.get(symbol);
    }
}