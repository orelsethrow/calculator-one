package com.example.calculator.operator;

import org.springframework.stereotype.Component;

@Component
public class DivideOperator implements Operator {
    @Override
    public double calculate(double num1, double num2) {
        if (num2 == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }
        return num1 / num2;
    }

    @Override
    public int getPriority() {
        return 2;
    }
}
