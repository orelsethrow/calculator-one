package com.example.calculator.operator;

public class MultiplyOperator implements Operator {
    @Override
    public double calculate(double left, double right) {
        return left * right;
    }
    
    @Override
    public String getSymbol() {
        return "*";
    }
    
    @Override
    public int getPrecedence() {
        return 2;
    }
}