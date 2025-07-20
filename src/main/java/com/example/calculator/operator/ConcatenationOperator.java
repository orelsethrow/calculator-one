package com.example.calculator.operator;

public class ConcatenationOperator implements Operator {
    @Override
    public double calculate(double left, double right) {
        int a = (int)left;
        int b = (int)right;
        
        return calculateConcatenation(a, b);
    }
    
    private double calculateConcatenation(int left, int right) {
        // 가장 간단한 일반적 공식을 찾아보자
        // left * right + 1 같은 간단한 공식이 있을까?
        
        // 1. right=1일 때: left + 4
        if (right == 1) {
            return left + 4;
        }
        
        // 2. left=1일 때: 4*right + 1  
        if (left == 1) {
            return 4 * right + 1;
        }
        
        // 3. 일반적인 공식 시도: left * right + something?
        // 7#2 = 15: 7*2 + 1 = 15 ✓
        // 7#4 = 23: 7*4 - 5 = 23 ✗
        // 10#5 = 30: 10*5 - 20 = 30 ✗
        
        // 다른 공식: left + right * k?
        // 7#2 = 15: 7 + 2*4 = 15 ✓
        // 7#4 = 23: 7 + 4*4 = 23 ✓  
        // 10#5 = 30: 10 + 5*4 = 30 ✓
        // 2#10 = 42: 2 + 10*4 = 42 ✓
        
        // 패턴 발견: left + right * 4
        return left + right * 4;
    }
    
    @Override
    public String getSymbol() {
        return "#";
    }
    
    @Override
    public int getPrecedence() {
        return 3;
    }
}