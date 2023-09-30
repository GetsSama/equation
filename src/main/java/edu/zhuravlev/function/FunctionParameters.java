package edu.zhuravlev.function;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FunctionParameters extends HashMap<String, BigDecimal> {
    @Override
    public BigDecimal put(String key, BigDecimal value) {
        return super.put(key, Objects.requireNonNull(value));
    }

    @Override
    public void putAll(Map<? extends String, ? extends BigDecimal> m) {
        for (var pair : m.entrySet()) {
            put(pair.getKey(), pair.getValue());
        }
    }
}
