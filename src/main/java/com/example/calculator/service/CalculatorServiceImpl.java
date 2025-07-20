package com.example.calculator.service;

import org.springframework.stereotype.Service;
import com.example.calculator.parser.ExpressionParser;
import com.example.calculator.operator.OperatorFactory;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    
    private final ExpressionParser parser;
    private final OperatorFactory operatorFactory;
    
    public CalculatorServiceImpl(ExpressionParser parser, OperatorFactory operatorFactory) {
        this.parser = parser;
        this.operatorFactory = operatorFactory;
    }
    
    @Override
    public double evaluate(String expression) {
        return parser.evaluate(expression, operatorFactory);
    }
}