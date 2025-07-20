package com.example.calculator.operator;

public interface Operator {
    double calculate(double left, double right);
    String getSymbol();
    int getPrecedence();
}