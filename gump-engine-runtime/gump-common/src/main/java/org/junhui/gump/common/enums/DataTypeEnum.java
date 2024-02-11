package org.junhui.gump.common.enums;

import java.util.Objects;

public enum DataTypeEnum {

    STRING("string", "文本"),

    NUMBER("number", "数字"),

    OBJECT("object", "对象"),

    BOOLEAN("boolean", "布尔"),

    ARRAY("array", "数组");

    private final String code;

    private final String desc;

    DataTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static DataTypeEnum enumByCode(String code) {
        for (DataTypeEnum item : DataTypeEnum.values()) {
            if (Objects.equals(item.code, code)) {
                return item;
            }
        }
        return null;
    }
}

