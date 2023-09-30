package edu.zhuravlev.function;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(3);
        Iterator<MathFunction> membersIter = members.iterator();

        sb.append(membersIter.next().toString().substring(2));

        while (membersIter.hasNext()) {
            sb.append(" ");
            sb.append(membersIter.next());
        }

        return sb.toString();
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PolynomialBuilder {
        private final List<MathFunction> members = new ArrayList<>(3);

        public Polynomial build() {
            if (members.size() < 2) {
                throw new IllegalStateException("Polynom must have at least two members");
            }

            return new Polynomial(Collections.unmodifiableList(members));
        }

        public PolynomialBuilder addMember(MathFunction mathFunction) {
            members.add(Objects.requireNonNull(mathFunction));
            return this;
        }

        public PolynomialBuilder addMember(Number number) {
            members.add(new ConstantFunction(Objects.requireNonNull(number)));
            return this;
        }
    }
}
