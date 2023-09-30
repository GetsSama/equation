package edu.zhuravlev.function;

import java.math.BigDecimal;
import java.util.Map;

@FunctionalInterface
public interface MathFunction {
    BigDecimal get(Map<String, BigDecimal> args);
}
