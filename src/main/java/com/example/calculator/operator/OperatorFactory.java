package com.example.calculator.operator;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;

@Component
public class OperatorFactory {
    private final Map<String, Operator> operatorMap;

    public OperatorFactory(
            ConcatenationOperator concatenationOperator,
            MultiplyOperator multiplyOperator,
            DivideOperator divideOperator,
            AddOperator addOperator,
            SubtractOperator subtractOperator) {
        
        operatorMap = new HashMap<>();
        operatorMap.put("#", concatenationOperator);
        operatorMap.put("*", multiplyOperator);
        operatorMap.put("/", divideOperator);
        operatorMap.put("+", addOperator);
        operatorMap.put("-", subtractOperator);
    }

    public Operator getOperator(String symbol) {
        Operator operator = operatorMap.get(symbol);
        if (operator == null) {
            throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + symbol);
        }
        return operator;
    }

    /**
     * 새로운 연산자를 등록합니다.
     * 
     * @param symbol 연산자 기호
     * @param operator 연산자 구현체
     */
    public void registerOperator(String symbol, Operator operator) {
        operatorMap.put(symbol, operator);
    }
}
