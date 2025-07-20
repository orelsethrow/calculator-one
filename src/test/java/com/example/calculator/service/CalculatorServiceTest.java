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

    @Test
    @DisplayName("7#2 = 15 확인")
    void testNewConcatenation1() {
        String expression = "7#2";
        double result = calculatorService.evaluate(expression);
        assertEquals(15.0, result);
    }

    @Test
    @DisplayName("10#5 = 30 확인")
    void testNewConcatenation2() {
        String expression = "10#5";
        double result = calculatorService.evaluate(expression);
        assertEquals(30.0, result);
    }

    @Test
    @DisplayName("1#9 = 37 확인")
    void testNewConcatenation3() {
        String expression = "1#9";
        double result = calculatorService.evaluate(expression);
        assertEquals(37.0, result);
    }

    @Test
    @DisplayName("1#1 = 5 확인")
    void testPattern1() {
        String expression = "1#1";
        double result = calculatorService.evaluate(expression);
        assertEquals(5.0, result);
    }

    @Test
    @DisplayName("1#2 = 9 확인")
    void testPattern2() {
        String expression = "1#2";
        double result = calculatorService.evaluate(expression);
        assertEquals(9.0, result);
    }

    @Test
    @DisplayName("1#3 = 13 확인 (재확인)")
    void testPattern3() {
        String expression = "1#3";
        double result = calculatorService.evaluate(expression);
        assertEquals(13.0, result);
    }

    @Test
    @DisplayName("1#11 = 45 확인")
    void testPattern4() {
        String expression = "1#11";
        double result = calculatorService.evaluate(expression);
        assertEquals(45.0, result);
    }

    @Test
    @DisplayName("2#10 = 42 확인")
    void testPattern5() {
        String expression = "2#10";
        double result = calculatorService.evaluate(expression);
        assertEquals(42.0, result);
    }

    @Test
    @DisplayName("1#0 = 1 확인")
    void testPattern6() {
        String expression = "1#0";
        double result = calculatorService.evaluate(expression);
        assertEquals(1.0, result);
    }

    @Test
    @DisplayName("3#(-1) = -1 확인")
    void testPattern7() {
        String expression = "3#-1";
        double result = calculatorService.evaluate(expression);
        assertEquals(-1.0, result);
    }

    @Test
    @DisplayName("4#1 = 8 확인")
    void testPattern8() {
        String expression = "4#1";
        double result = calculatorService.evaluate(expression);
        assertEquals(8.0, result);
    }

    @Test
    @DisplayName("5#1 = 9 확인")
    void testPattern9() {
        String expression = "5#1";
        double result = calculatorService.evaluate(expression);
        assertEquals(9.0, result);
    }

    @Test
    @DisplayName("6#1 = 10 확인")
    void testPattern10() {
        String expression = "6#1";
        double result = calculatorService.evaluate(expression);
        assertEquals(10.0, result);
    }
}
