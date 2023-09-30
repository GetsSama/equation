package edu.zhuravlev;

import edu.zhuravlev.function.ConstantFunction;
import edu.zhuravlev.function.Polynomial;
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

        ConstantFunction _3 = new ConstantFunction(3);

        Polynomial func = Polynomial.builder()
                .addMember(_2x)
                .addMember(_3)
                .build();

        System.out.println(func.get(vars));
    }
}