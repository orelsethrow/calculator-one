package com.example.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorServiceImpl();
    }

    @Test
    @DisplayName("서비스가 정상적으로 초기화되는지 확인")
    void testServiceInitialization() {
        assertNotNull(calculatorService);
    }

    @Test
    @DisplayName("현재는 구현되지 않은 상태이므로 UnsupportedOperationException 발생 확인")
    void testNotImplementedYet() {
        String expression = "2+2";
        assertThrows(UnsupportedOperationException.class, () -> {
            calculatorService.evaluate(expression);
        });
    }
}
