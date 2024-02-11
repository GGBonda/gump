package org.junhui.gump.flow.core.enums;

import org.apache.commons.lang3.StringUtils;
import org.junhui.gump.common.enums.DataTypeEnum;

import java.util.function.Function;

public enum FlowParamDefaultValidationEnums {
    NOT_NULL("not_null", DataTypeEnum.STRING, value -> StringUtils.isNotEmpty((String) value));

    private final String code;

    private final DataTypeEnum paramType;

    private final Function<Object, Boolean> strategy;

    FlowParamDefaultValidationEnums (String code, DataTypeEnum paramType, Function<Object, Boolean> strategy) {
        this.code = code;
        this.paramType = paramType;
        this.strategy = strategy;
    }

    public String getCode() {
        return code;
    }

    public DataTypeEnum getParamType() {
        return paramType;
    }

    public Function<Object, Boolean> getStrategy() {
        return strategy;
    }
}
