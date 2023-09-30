package edu.zhuravlev.function;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class SimpleFunction implements MathFunction {
    @Builder.Default
    private final Sign sign = Sign.PLUS;
    @Builder.Default
    private final MathFunction multiplier = new ConstantFunction(BigDecimal.ONE);
    @Builder.Default
    private final MathFunction power = new ConstantFunction(BigDecimal.ONE);
    private final String variable;

    public static CustomSimpleFunctionBuilder builder() {
        return new CustomSimpleFunctionBuilder();
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(6);

        if (sign == Sign.PLUS) {
            sb.append("+ ");
        } else {
            sb.append("- ");
        }

        if (multiplier instanceof ConstantFunction) {
            if (multiplier.get(null).doubleValue() != 1) {
                sb.append(multiplier);
            }
        } else {
            sb.append("(");
            sb.append(multiplier);
            sb.append(")");
        }

        sb.append(variable);

        if (power instanceof ConstantFunction) {
            if (power.get(null).doubleValue() != 1) {
                sb.append("^");
                sb.append(power);
            }
        } else {
            sb.append("^(");
            sb.append(power);
            sb.append(")");
        }

        return sb.toString();
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

    public static class CustomSimpleFunctionBuilder extends SimpleFunctionBuilder {
        @Override
        public CustomSimpleFunctionBuilder sign(Sign sign) {
            return (CustomSimpleFunctionBuilder) super.sign(sign);
        }

        @Override
        public CustomSimpleFunctionBuilder variable(String variable) {
            return (CustomSimpleFunctionBuilder) super.variable(variable);
        }

        @Override
        public CustomSimpleFunctionBuilder multiplier(MathFunction multiplier) {
            return (CustomSimpleFunctionBuilder) super.multiplier(multiplier);
        }

        public CustomSimpleFunctionBuilder multiplier(Number multiplier) {
            return multiplier(new ConstantFunction(multiplier));
        }

        @Override
        public CustomSimpleFunctionBuilder power(MathFunction power) {
            return (CustomSimpleFunctionBuilder) super.power(power);
        }

        public CustomSimpleFunctionBuilder power(Number power) {
            return power(new ConstantFunction(power));
        }
    }

    public enum Sign {
        PLUS, MINUS
    }
}
