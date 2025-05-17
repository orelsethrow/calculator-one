package com.example.calculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public double evaluate(String expression) {
        // 공백 제거
        expression = expression.replaceAll("\\s+", "");
        
        try {
            // 연산자와 숫자를 분리
            String[] tokens = expression.split("(?<=[-+*/#])|(?=[-+*/#])");
            
            if (tokens.length < 3) {
                throw new IllegalArgumentException("Invalid expression format");
            }
            
            // 숫자와 연산자를 저장할 리스트
            java.util.List<String> operators = new java.util.ArrayList<>();
            java.util.List<Double> numbers = new java.util.ArrayList<>();
            
            // 첫 번째 숫자 추가
            numbers.add(Double.parseDouble(tokens[0]));
            
            // 연산자와 숫자를 분리하여 저장
            for (int i = 1; i < tokens.length; i += 2) {
                operators.add(tokens[i]);
                numbers.add(Double.parseDouble(tokens[i + 1]));
            }
            
            // 1단계: # 연산 처리 (가장 높은 우선순위)
            for (int i = 0; i < operators.size(); i++) {
                String op = operators.get(i);
                if (op.equals("#")) {
                    double num1 = numbers.get(i);
                    double num2 = numbers.get(i + 1);
                    
                    // 두 숫자를 이어붙인 결과 계산
                    String concatenated = String.format("%.0f%.0f", num1, num2);
                    double concatenatedValue = Double.parseDouble(concatenated);
                    // 일반 연산 결과 계산 (b * 4를 더하기)
                    double calculatedValue = num1 + (num2 * 4);
                    // 더 작은 값을 선택
                    double result = Math.min(concatenatedValue, calculatedValue);
                    
                    // 계산 결과를 첫 번째 위치에 저장하고, 두 번째 숫자 제거
                    numbers.set(i, result);
                    numbers.remove(i + 1);
                    operators.remove(i);
                    i--; // 현재 위치 다시 검사
                }
            }
            
            // 2단계: *, / 연산 처리
            for (int i = 0; i < operators.size(); i++) {
                String op = operators.get(i);
                if (op.equals("*") || op.equals("/")) {
                    double num1 = numbers.get(i);
                    double num2 = numbers.get(i + 1);
                    double result;
                    
                    if (op.equals("*")) {
                        result = num1 * num2;
                    } else {
                        if (num2 == 0) {
                            throw new IllegalArgumentException("Division by zero");
                        }
                        result = num1 / num2;
                    }
                    
                    // 계산 결과를 첫 번째 위치에 저장하고, 두 번째 숫자 제거
                    numbers.set(i, result);
                    numbers.remove(i + 1);
                    operators.remove(i);
                    i--; // 현재 위치 다시 검사
                }
            }
            
            // 2단계: +, - 연산 처리
            double result = numbers.get(0);
            for (int i = 0; i < operators.size(); i++) {
                String op = operators.get(i);
                double num = numbers.get(i + 1);
                
                if (op.equals("+")) {
                    result += num;
                } else if (op.equals("-")) {
                    result -= num;
                } else {
                    throw new IllegalArgumentException("Unsupported operator: " + op);
                }
            }
            
            return result;
            
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid expression format");
        }
    }
}
