package edu.zhuravlev;

import edu.zhuravlev.equation.Equation;
import edu.zhuravlev.function.*;

import java.math.BigDecimal;
import java.util.Map;

public class Main {
    private static FunctionParameters vars = new FunctionParameters();

    static {
        vars.putAll(Map.of(
                "x", BigDecimal.valueOf(3),
                "y", BigDecimal.valueOf(5)));
    }

    public static void main(String[] args) {
        MathFunction _x_2 = SimpleFunction.builder().power(2).variable("x").build();
        MathFunction _2x = SimpleFunction.builder().multiplier(2.5).variable("x").build();
        MathFunction _y = SimpleFunction.builder().variable("y").sign(SimpleFunction.Sign.MINUS).build();

        MathFunction func = Polynomial.builder()
                .addMember(_x_2)
                .addMember(_2x)
                .addMember(_y)
                .build();

        System.out.println(func.get(vars));
        System.out.println(func);

        Equation equation = new Equation(func, new ConstantFunction(11.5));

        System.out.println(equation.isEquation(vars));
    }
}