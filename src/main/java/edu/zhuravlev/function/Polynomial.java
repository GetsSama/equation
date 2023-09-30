package edu.zhuravlev.function;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Polynomial implements MathFunction {
    private final List<MathFunction> members;

    @Override
    public BigDecimal get(Map<String, BigDecimal> args) {
        double result = 0;

        for (MathFunction member : members) {
            result += member.get(args).doubleValue();
        }

        return BigDecimal.valueOf(result);
    }

    public static PolynomialBuilder builder() {
        return new PolynomialBuilder();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PolynomialBuilder {
        private final List<MathFunction> members = new ArrayList<>(3);

        public Polynomial build() {
            if (members.size() < 2) {
                throw new IllegalStateException("Polynom must have at least two members");
            }

            return new Polynomial(members);
        }

        public PolynomialBuilder addMember(MathFunction mathFunction) {
            members.add(Objects.requireNonNull(mathFunction));
            return this;
        }
    }
}
