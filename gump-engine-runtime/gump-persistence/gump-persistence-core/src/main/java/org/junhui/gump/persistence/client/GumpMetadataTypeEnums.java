package org.junhui.gump.persistence.client;

import java.util.Objects;

public enum GumpMetadataTypeEnums {

    FLOW("flow", "流程"),
    RPC("rpc", "远程过程调用");

    private final String code;
    private final String desc;

    GumpMetadataTypeEnums(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static GumpMetadataTypeEnums enumByCode(String code) {
        for (GumpMetadataTypeEnums item : GumpMetadataTypeEnums.values()) {
            if (Objects.equals(code, item.code)) {
                return item;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
