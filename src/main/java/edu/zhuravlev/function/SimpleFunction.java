package edu.zhuravlev.function;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimpleFunction implements MathFunction {
    @Builder.Default
    private final Sign sign = Sign.PLUS;
    @Builder.Default
    private final MathFunction multiplier = new ConstantFunction(BigDecimal.ONE);
    @Builder.Default
    private final MathFunction power = new ConstantFunction(BigDecimal.ONE);
    private final String variable;

    @Override
    public BigDecimal get(Map<String, BigDecimal> args) {
        BigDecimal argument = args.get(variable);
        BigDecimal multiplierVal = multiplier.get(args);
        BigDecimal powerVal = power.get(args);

        if (argument == null) {
            throw new IllegalArgumentException("No args with name '" + variable + "' was provided");
        }

        return calculate(argument, multiplierVal, powerVal);
    }

    private BigDecimal calculate(BigDecimal arg, BigDecimal multi, BigDecimal pow) {
        double value = arg.doubleValue();
        double multiVal = multi.doubleValue();
        double powVal = pow.doubleValue();

        double result;

        result = Math.pow(value, powVal) * multiVal;

        if (sign == Sign.PLUS) {
            return BigDecimal.valueOf(result);
        }

        return BigDecimal.valueOf(-result);
    }

    public enum Sign {
        PLUS, MINUS
    }
}
