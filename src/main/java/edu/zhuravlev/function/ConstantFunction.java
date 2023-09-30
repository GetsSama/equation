package edu.zhuravlev.function;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ConstantFunction implements MathFunction {
    private final BigDecimal value;

    public ConstantFunction(BigDecimal value) {
        this.value = value;
    }

    public ConstantFunction(Number value) {
        this.value = BigDecimal.valueOf(value.doubleValue());
    }

    @Override
    public BigDecimal get(FunctionParameters args) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
