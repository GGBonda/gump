package org.junhui.gump.flow.poet.compiler.template;


import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.common.enums.DataTypeEnum;

import java.util.Collections;
import java.util.List;

public class BooleanParamTemplate extends FlowParamJavaCodeTemplate {

    public BooleanParamTemplate(FlowParam flowParam, String parentPointerChain) {
        super(flowParam, parentPointerChain);
    }

    @Override
    public List<String> javaCode(boolean needAutoMapping) {
        return Collections.singletonList(
                String.format("Boolean %s = %s", pointer(), DATA_TRANSFER(PARAM_VALUE(needAutoMapping)))
        );
    }

    @Override
    public List<DataTypeEnum> acceptDataType() {
        return Collections.singletonList(DataTypeEnum.BOOLEAN);
    }
}