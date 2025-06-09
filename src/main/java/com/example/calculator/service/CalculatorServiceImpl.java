package com.example.calculator.service;

import org.springframework.stereotype.Service;
import com.example.calculator.parser.ExpressionParser;
import com.example.calculator.parser.Token;
import com.example.calculator.parser.TokenType;
import com.example.calculator.operator.Operator;
import com.example.calculator.operator.OperatorFactory;
import java.util.*;

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
        // 수식을 토큰으로 파싱
        Token[] tokens = parser.parse(expression);
        
        // 토큰을 숫자와 연산자로 분리
        List<Double> numbers = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        List<Integer> priorities = new ArrayList<>();

        // 첫 번째 숫자는 바로 추가
        numbers.add(Double.parseDouble(tokens[0].getValue()));

        // 나머지 토큰 처리
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i].getValue();
            operators.add(operator);
            priorities.add(operatorFactory.getOperator(operator).getPriority());
            numbers.add(Double.parseDouble(tokens[i + 1].getValue()));
        }

        // 우선순위가 높은 순서대로 계산
        while (!operators.isEmpty()) {
            // 가장 높은 우선순위 찾기
            int maxPriority = Collections.max(priorities);
            
            // 해당 우선순위의 연산자들 처리
            for (int i = 0; i < operators.size(); i++) {
                if (priorities.get(i) == maxPriority) {
                    String operator = operators.get(i);
                    double num1 = numbers.get(i);
                    double num2 = numbers.get(i + 1);
                    
                    // 연산 수행
                    double result = operatorFactory.getOperator(operator)
                            .calculate(num1, num2);
                    
                    // 결과 저장 및 사용된 피연산자 제거
                    numbers.set(i, result);
                    numbers.remove(i + 1);
                    operators.remove(i);
                    priorities.remove(i);
                    i--; // 인덱스 조정
                }
            }
        }

        return numbers.get(0);
    }
}
