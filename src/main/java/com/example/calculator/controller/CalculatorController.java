package com.example.calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring REST API!";
    }
}
