package com.example.calculator.operator;

import org.springframework.stereotype.Component;

@Component
public class ConcatenationOperator implements Operator {
    @Override
    public double calculate(double num1, double num2) {
        // 이어붙이기 연산과 일반 연산 중 더 작은 값을 반환
        String concatenated = String.format("%.0f%.0f", num1, num2);
        double concatenatedValue = Double.parseDouble(concatenated);
        double calculatedValue = num1 + (num2 * 4);
        
        return Math.min(concatenatedValue, calculatedValue);
    }

    @Override
    public int getPriority() {
        return 3; // 최상위 우선순위
    }
}
