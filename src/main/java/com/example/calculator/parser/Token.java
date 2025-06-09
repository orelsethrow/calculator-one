package com.example.calculator.parser;

public class Token {
    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public double getNumberValue() {
        if (type != TokenType.NUMBER) {
            throw new IllegalStateException("토큰이 숫자 타입이 아닙니다.");
        }
        return Double.parseDouble(value);
    }

    @Override
    public String toString() {
        return "Token{type=" + type + ", value='" + value + "'}";
    }
}
