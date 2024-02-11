package org.junhui.gump.flow.core.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 在 flow 运行过程中，一个 step 的数据
 * */
public class StepValue {

    private final String code;

    private final Map<String, Object> value;

    public StepValue(String code) {
        this.code = code;
        this.value = new HashMap<>();
    }

    public void put(String key, Object value) {
        getValue().put(key, value);
    }

    public Object get(String key) {
        return getValue().get(key);
    }

    public String getCode() {
        return code;
    }

    public Map<String, Object> getValue() {
        return value;
    }
}
