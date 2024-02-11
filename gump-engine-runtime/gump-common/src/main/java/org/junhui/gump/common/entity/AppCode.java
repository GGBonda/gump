package org.junhui.gump.common.entity;

import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class AppCode implements Serializable {
    private static final long serialVersionUID = -4549724544794062553L;

    private String code;

    private AppCode(){}

    public AppCode(String appCode) {
        if (StringUtils.isEmpty(appCode)) {
            throw new IllegalArgumentException("appCode 不能为空");
        }
        this.code = appCode;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppCode)) return false;
        AppCode appCode = (AppCode) o;
        return Objects.equal(code, appCode.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
}
