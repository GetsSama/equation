package edu.zhuravlev.equation;

import edu.zhuravlev.function.FunctionParameters;
import edu.zhuravlev.function.MathFunction;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Equation {
    private final MathFunction leftHand;
    private final MathFunction rightHand;

    public Equation(MathFunction leftHand, MathFunction rightHand) {
        this.leftHand = Objects.requireNonNull(leftHand);
        this.rightHand = Objects.requireNonNull(rightHand);
    }

    public boolean isEquation(FunctionParameters args) {
        return leftHand.get(args).compareTo(rightHand.get(args)) == 0;
    }
}
