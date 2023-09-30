package edu.zhuravlev.function;

import java.math.BigDecimal;

@FunctionalInterface
public interface MathFunction {
    BigDecimal get(FunctionParameters args);
}
