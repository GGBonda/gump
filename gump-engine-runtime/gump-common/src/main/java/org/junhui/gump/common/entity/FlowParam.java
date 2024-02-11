package org.junhui.gump.common.entity;


import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.junhui.gump.common.enums.DataTypeEnum;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class FlowParam {

    private String code;

    private String type;

    private List<String> validations;

    private String value;

    private String defaultValue;

    private List<FlowParam> children;

    private String comment;

    public DataTypeEnum dataType() {
        return DataTypeEnum.enumByCode(this.type);
    }

    public boolean hasChildren() {
        DataTypeEnum dataTypeEnum = dataType();
        return (Objects.equals(dataTypeEnum, DataTypeEnum.OBJECT) || Objects.equals(dataTypeEnum, DataTypeEnum.ARRAY)) && CollectionUtils.isNotEmpty(children);
    }
}
