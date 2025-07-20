package com.example.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.calculator.parser.ExpressionParser;
import com.example.calculator.operator.*;

@Configuration
public class CalculatorConfig {
    
    @Bean
    public ExpressionParser expressionParser() {
        return new ExpressionParser();
    }
    
    @Bean
    public OperatorFactory operatorFactory() {
        return new OperatorFactory(
            new ConcatenationOperator(),
            new MultiplyOperator(),
            new DivideOperator(),
            new AddOperator(),
            new SubtractOperator()
        );
    }
}