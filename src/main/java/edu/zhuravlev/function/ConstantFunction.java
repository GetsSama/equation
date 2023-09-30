package edu.zhuravlev.function;

import java.math.BigDecimal;
import java.util.Map;


public class ConstantFunction implements MathFunction {
    private final BigDecimal value;

    public ConstantFunction(BigDecimal value) {
        this.value = value;
    }

    public ConstantFunction(Number value) {
        this.value = BigDecimal.valueOf(value.doubleValue());
    }

    @Override
    public BigDecimal get(Map<String, BigDecimal> args) {
        return value;
    }
}
