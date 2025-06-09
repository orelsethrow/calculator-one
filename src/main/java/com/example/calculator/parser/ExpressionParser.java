package com.example.calculator.parser;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ExpressionParser {
    private static final Pattern OPERATOR_PATTERN = Pattern.compile("[-+*/&#]");

    public Token[] parse(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("수식이 비어있습니다.");
        }

        // 공백 제거
        expression = expression.replaceAll("\\s+", "");
        
        // 연산자 기준으로 분리
        String[] parts = expression.split("(?<=[-+*/&#])|(?=[-+*/&#])");
        List<Token> tokens = new ArrayList<>();

        for (String part : parts) {
            if (part.isEmpty()) {
                continue;
            }

            if (OPERATOR_PATTERN.matcher(part).matches()) {
                tokens.add(new Token(TokenType.OPERATOR, part));
            } else {
                try {
                    // 숫자 유효성 검사
                    Double.parseDouble(part);
                    tokens.add(new Token(TokenType.NUMBER, part));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("잘못된 숫자 형식: " + part);
                }
            }
        }

        // 유효성 검사
        validateTokens(tokens);

        return tokens.toArray(new Token[0]);
    }

    private void validateTokens(List<Token> tokens) {
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("수식이 비어있습니다.");
        }

        // 첫 번째와 마지막 토큰은 숫자여야 함
        if (tokens.get(0).getType() != TokenType.NUMBER || 
            tokens.get(tokens.size() - 1).getType() != TokenType.NUMBER) {
            throw new IllegalArgumentException("수식은 숫자로 시작하고 끝나야 합니다.");
        }

        // 연산자와 숫자가 번갈아가며 나와야 함
        for (int i = 0; i < tokens.size() - 1; i++) {
            if (tokens.get(i).getType() == tokens.get(i + 1).getType()) {
                throw new IllegalArgumentException("연산자와 숫자가 번갈아가며 나와야 합니다.");
            }
        }
    }
}
