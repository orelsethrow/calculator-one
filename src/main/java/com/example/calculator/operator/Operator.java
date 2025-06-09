package com.example.calculator.operator;

public interface Operator {
    /**
     * 두 숫자에 대해 연산을 수행합니다.
     *
     * @param num1 첫 번째 피연산자
     * @param num2 두 번째 피연산자
     * @return 연산 결과
     */
    double calculate(double num1, double num2);

    /**
     * 연산자의 우선순위를 반환합니다.
     * 높은 숫자가 더 높은 우선순위를 의미합니다.
     * 기본 우선순위:
     * 3: # (이어붙이기)
     * 2: *, /
     * 1: +, -
     *
     * @return 연산자 우선순위
     */
    default int getPriority() {
        return 1;
    }
}
