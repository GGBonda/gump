package org.junhui.gump.flow.poet.compiler.template;


import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.common.enums.DataTypeEnum;

import java.util.Collections;
import java.util.List;

public class StringParamTemplate extends FlowParamJavaCodeTemplate {


    public StringParamTemplate(FlowParam flowParam, String parentPointerChain) {
        super(flowParam, parentPointerChain);
    }

    @Override
    public List<String> javaCode(boolean needAutoMapping) {
        return Collections.singletonList(
                String.format("String %s = %s", pointer(), DATA_TRANSFER(PARAM_VALUE(needAutoMapping)))
        );
    }

    @Override
    public List<DataTypeEnum> acceptDataType() {
        return Collections.singletonList(DataTypeEnum.STRING);
    }
}
