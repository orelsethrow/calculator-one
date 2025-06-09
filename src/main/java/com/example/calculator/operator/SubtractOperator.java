package com.example.calculator.operator;

import org.springframework.stereotype.Component;

@Component
public class SubtractOperator implements Operator {
    @Override
    public double calculate(double num1, double num2) {
        return num1 - num2;
    }

    @Override
    public int getPriority() {
        return 1; // 기본 우선순위
    }
}
