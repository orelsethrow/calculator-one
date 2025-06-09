package com.example.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.calculator.parser.ExpressionParser;
import com.example.calculator.operator.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorServiceTest {

    private CalculatorService calculatorService;
    private ExpressionParser parser;
    private OperatorFactory operatorFactory;

    @BeforeEach
    void setUp() {
        parser = new ExpressionParser();
        operatorFactory = new OperatorFactory(
            new ConcatenationOperator(),
            new MultiplyOperator(),
            new DivideOperator(),
            new AddOperator(),
            new SubtractOperator()
        );
        calculatorService = new CalculatorServiceImpl(parser, operatorFactory);
    }

    @Test
    @DisplayName("서비스가 정상적으로 초기화되는지 확인")
    void testServiceInitialization() {
        assertNotNull(calculatorService);
    }

    @Test
    @DisplayName("2+2 = 4 확인")
    void testAnotherAddition() {
        String expression = "2+2";
        double result = calculatorService.evaluate(expression);
        assertEquals(4.0, result);
    }

    @Test
    @DisplayName("1+1 = 2 확인")
    void testSimpleAddition() {
        String expression = "1+1";
        double result = calculatorService.evaluate(expression);
        assertEquals(2.0, result);
    }

    @Test
    @DisplayName("1+1+1 = 3 확인")
    void testMultipleAddition() {
        String expression = "1+1+1";
        double result = calculatorService.evaluate(expression);
        assertEquals(3.0, result);
    }

    @Test
    @DisplayName("3-1 = 2 확인")
    void testSubtraction() {
        String expression = "3-1";
        double result = calculatorService.evaluate(expression);
        assertEquals(2.0, result);
    }

    @Test
    @DisplayName("2*3 = 6 확인")
    void testMultiplication() {
        String expression = "2*3";
        double result = calculatorService.evaluate(expression);
        assertEquals(6.0, result);
    }

    @Test
    @DisplayName("6/2 = 3 확인")
    void testDivision() {
        String expression = "6/2";
        double result = calculatorService.evaluate(expression);
        assertEquals(3.0, result);
    }

    @Test
    @DisplayName("1 + 1 = 2 확인")
    void testAdditionWithSpaces() {
        String expression = "1 + 1";
        double result = calculatorService.evaluate(expression);
        assertEquals(2.0, result);
    }

    @Test
    @DisplayName("1 + 3 * 5 = 16 확인")
    void testAdditionAndMultiplication() {
        String expression = "1 + 3 * 5";
        double result = calculatorService.evaluate(expression);
        assertEquals(16.0, result);
    }

    @Test
    @DisplayName("1 + 3 * 5 - 2 = 14 확인")
    void testComplexExpression() {
        String expression = "1 + 3 * 5 - 2";
        double result = calculatorService.evaluate(expression);
        assertEquals(14.0, result);
    }

    @Test
    @DisplayName("1#3 = 13 확인")
    void testNewOperation() {
        String expression = "1#3";
        double result = calculatorService.evaluate(expression);
        assertEquals(13.0, result);
    }

    @Test
    @DisplayName("7#1 = 11 확인")
    void testNewOperation2() {
        String expression = "7#1";
        double result = calculatorService.evaluate(expression);
        assertEquals(11.0, result);
    }

    @Test
    @DisplayName("3 * 7#1 + 2 = 35 확인")
    void testComplexExpressionWithNewOperation() {
        String expression = "3 * 7#1 + 2";
        double result = calculatorService.evaluate(expression);
        assertEquals(35.0, result);
    }

    @Test
    @DisplayName("7#4 * 7 - 2 = 159 확인")
    void testComplexExpressionWithNewOperation2() {
        String expression = "7#4 * 7 - 2";
        double result = calculatorService.evaluate(expression);
        assertEquals(159.0, result);
    }
}
