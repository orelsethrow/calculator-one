package com.example.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.calculator.service.CalculatorService;

import java.util.Map;

@RestController
public class CalculatorController {
    
    private final CalculatorService calculatorService;

    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }
    
    @GetMapping("/calculate")
    public Map<String, Double> calculate(@RequestParam String q) {
        double result = calculatorService.evaluate(q);
        return Map.of("result", result);
    }
}
