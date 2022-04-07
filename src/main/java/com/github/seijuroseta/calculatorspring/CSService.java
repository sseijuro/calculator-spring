package com.github.seijuroseta.calculatorspring;

import org.springframework.stereotype.Service;

@Service
public class CSService {
    public CSModel.Result solve(CSOperator.MathOperator operator, Float lhs, Float rhs) {
        return operator.execute(new CSModel.Pair(lhs, rhs));
    }

    public CSModel.Result solve(CSOperator.NotationOperator operator, String input) {
        return operator.execute(input);
    }
}
