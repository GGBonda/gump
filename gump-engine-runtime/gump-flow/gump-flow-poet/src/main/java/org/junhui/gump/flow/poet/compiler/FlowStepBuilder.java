package org.junhui.gump.flow.poet.compiler;

import org.apache.commons.collections4.CollectionUtils;
import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.common.enums.DataTypeEnum;
import org.junhui.gump.flow.poet.compiler.template.FlowParamJavaCodeTemplate;

import java.util.*;

import static org.junhui.gump.flow.core.runner.method_constants.BaseRunnerMethodConstants.*;

public class FlowStepBuilder {

    public List<String> compileParam(String paramName, FlowParam flowParam, boolean needAutoMapping) {
        if (flowParam == null) {
            return Collections.emptyList();
        }

        FlowParamJavaCodeTemplate template = FlowParamJavaCodeTemplate.getTemplateInstance(flowParam, null);

        List<String> codes = new ArrayList<>(Objects.requireNonNull(template).javaCode(needAutoMapping));
        codes.add(SET_CURRENT_STEP_VALUE(paramName, template.pointer()));
        return codes;
    }

    public List<String> compileParam(String paramName, List<FlowParam> childrenParams, boolean needAutoMapping) {
        if (CollectionUtils.isEmpty(childrenParams)) {
            return Collections.emptyList();
        }

        FlowParam virtualFlowParam = new FlowParam();
        virtualFlowParam.setCode(paramName);
        virtualFlowParam.setType(DataTypeEnum.OBJECT.getCode());
        virtualFlowParam.setChildren(childrenParams);

        FlowParamJavaCodeTemplate template = FlowParamJavaCodeTemplate.getTemplateInstance(virtualFlowParam, null);

        List<String> codes = new ArrayList<>(Objects.requireNonNull(template).javaCode(needAutoMapping));
        codes.add(SET_CURRENT_STEP_VALUE(paramName, template.pointer()));
        return codes;
    }

    public List<String> nextPush(String nextStep) {
        return Collections.singletonList(NEXT_PUSH(nextStep));
    }
}
