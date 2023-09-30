package edu.zhuravlev;

import edu.zhuravlev.function.ConstantFunction;
import edu.zhuravlev.function.SimpleFunction;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
    private static Map<String, BigDecimal> vars = Map.of("x", BigDecimal.valueOf(3));

    public static void main(String[] args) {
        SimpleFunction _2x = SimpleFunction.builder()
                .multiplier(new ConstantFunction(2))
                .variable("x")
                .power(new ConstantFunction(1))
                .build();

        System.out.println(_2x.get(vars));
    }
}