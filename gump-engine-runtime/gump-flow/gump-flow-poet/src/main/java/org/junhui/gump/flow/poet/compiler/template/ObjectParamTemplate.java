package org.junhui.gump.flow.poet.compiler.template;

import org.apache.commons.collections4.CollectionUtils;
import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.common.enums.DataTypeEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectParamTemplate extends FlowParamJavaCodeTemplate {

    private final List<FlowParamJavaCodeTemplate> childTemplates;

    public ObjectParamTemplate(FlowParam flowParam, String parentPointerChain) {
        super(flowParam, parentPointerChain);
        if (CollectionUtils.isNotEmpty(flowParam.getChildren())) {
            childTemplates = flowParam.getChildren().stream().map(childTemplate ->
                    FlowParamJavaCodeTemplate.getTemplateInstance(childTemplate, getPointerChain())
            ).collect(Collectors.toList());
        } else {
            childTemplates = Collections.emptyList();
        }
    }

    @Override
    public List<String> javaCode(boolean needAutoMapping) {
        List<String> codes = new ArrayList<>();

        codes.add(String.format("Map<String, Object> %s = %s", pointer(), DATA_TRANSFER(PARAM_VALUE(needAutoMapping))));
        if (CollectionUtils.isNotEmpty(this.childTemplates)) {
            for (FlowParamJavaCodeTemplate childTemplate: childTemplates) {
                codes.addAll(childTemplate.javaCode(needAutoMapping));
                codes.add(
                        String.format("%s.put(\"%s\", %s)", pointer(), childTemplate.getFlowParam().getCode(), pointer(childTemplate.getFlowParam()))
                );
            }
        }
        return codes;
    }

    @Override
    public List<DataTypeEnum> acceptDataType() {
        return Collections.singletonList(DataTypeEnum.OBJECT);
    }
}
