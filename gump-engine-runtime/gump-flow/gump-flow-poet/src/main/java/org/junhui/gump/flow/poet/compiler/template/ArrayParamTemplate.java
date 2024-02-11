package org.junhui.gump.flow.poet.compiler.template;

import org.apache.commons.collections4.CollectionUtils;
import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.common.enums.DataTypeEnum;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayParamTemplate extends FlowParamJavaCodeTemplate {

    private final List<FlowParamJavaCodeTemplate> childTemplates;

    public ArrayParamTemplate(FlowParam flowParam, String parentPointerChain) {
        super(flowParam, parentPointerChain);
        if (CollectionUtils.isNotEmpty(flowParam.getChildren())) {
            childTemplates = flowParam.getChildren().stream().map(childTemplate ->
                    FlowParamJavaCodeTemplate.getTemplateInstance(childTemplate, ROW_POINTER)
            ).collect(Collectors.toList());
        } else {
            childTemplates = Collections.emptyList();
        }
    }

    @Override
    public List<String> javaCode(boolean needAutoMapping) {
        List<String> codes = new ArrayList<>();

        codes.add(String.format("List<Object> %s = %s", pointer(), DATA_TRANSFER(PARAM_VALUE(needAutoMapping))));
        if (CollectionUtils.isNotEmpty(this.childTemplates)) {
            List<String> mapFunctionCodes = new ArrayList<>();
            mapFunctionCodes.add("Map<String, Object> item = new HashMap<>()");
            //mapFunctionCodes.add("if (row instanceof Map) { item.putAll(row); }");
            for (FlowParamJavaCodeTemplate childTemplate: childTemplates) {
                mapFunctionCodes.addAll(childTemplate.javaCode(needAutoMapping));
                mapFunctionCodes.add(
                        String.format("item.put(\"%s\", %s)", childTemplate.getFlowParam().getCode(), pointer(childTemplate.getFlowParam()))
                );
            }
            mapFunctionCodes.add("return item;");
            codes.add(String.format("%s = %s.stream().map(%s -> {%s}).collect(Collectors.toList())", pointer(), pointer(), ROW_POINTER, String.join(";", mapFunctionCodes)));
        }
        return codes;
    }

    @Override
    public List<DataTypeEnum> acceptDataType() {
        return Collections.singletonList(DataTypeEnum.ARRAY);
    }
}