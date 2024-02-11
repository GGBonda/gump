package org.junhui.gump.flow.poet.compiler.template;

import org.apache.commons.lang3.StringUtils;
import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.common.enums.DataTypeEnum;
import org.junhui.gump.flow.poet.compiler.expression.ANTLRCompiler;

import java.util.*;

import static org.junhui.gump.flow.core.runner.method_constants.BaseRunnerMethodConstants.GET_PRE_STEP_OUTPUT;
import static org.junhui.gump.flow.core.runner.method_constants.DataTranslatorMethodConstants.*;
import static org.junhui.gump.flow.core.runner.method_constants.GumpExpressionOperationMethodConstants.*;

public abstract class FlowParamJavaCodeTemplate {

    protected final static String ROW_POINTER = "row";

    protected final static String PRE_STEP_OUTPUT_POINTER = "$";

    private final String parentPointerChain;

    private final FlowParam flowParam;

    protected FlowParam getFlowParam() {
        return flowParam;
    }

    public FlowParamJavaCodeTemplate(FlowParam flowParam, String parentPointerChain) {
        if (!acceptDataType().contains(flowParam.dataType())) {
            throw new IllegalArgumentException(String.format("can't accept %s param", flowParam.getType()));
        }
        this.flowParam = flowParam;
        this.parentPointerChain = parentPointerChain;
    }

    protected String getPointerChain() {
        if (StringUtils.isEmpty(this.parentPointerChain)) {
            return PRE_STEP_OUTPUT_POINTER;
        }
        return this.parentPointerChain +"."+ this.flowParam.getCode();
    }

    public static FlowParamJavaCodeTemplate getTemplateInstance(FlowParam flowParam, String parentPointerChain) {
        switch (flowParam.dataType()) {
            case STRING:
                return new StringParamTemplate(flowParam, parentPointerChain);
            case NUMBER:
                return new NumberParamTemplate(flowParam, parentPointerChain);
            case BOOLEAN:
                return new BooleanParamTemplate(flowParam, parentPointerChain);
            case OBJECT:
                return new ObjectParamTemplate(flowParam, parentPointerChain);
            case ARRAY:
                return new ArrayParamTemplate(flowParam, parentPointerChain);
            default:
        }
        return null;
    }

    public String pointer() {
        return pointer(this.flowParam);
    }

    protected String pointer(FlowParam flowParam) {
        return flowParam.getCode() +"_"+ flowParam.hashCode();
    }

    protected String PARAM_VALUE(boolean needAutoMapping) {
        if (needAutoMapping) {
            return String.format(
                    "Optional.ofNullable(Optional.ofNullable(%s).orElse(%s)).orElse(%s)",
                    GUMP_EXPR_VALUE(),
                    AUTO_MAPPING_VALUE(),
                    DEFAULT_VALUE()
            );
        } else {
            return String.format(
                    "Optional.ofNullable(%s).orElse(%s)",
                    GUMP_EXPR_VALUE(),
                    DEFAULT_VALUE()
            );
        }
    }

    private String GUMP_EXPR_VALUE() {
        String expr = this.flowParam.getValue();
        String exprCompileRes = "null";
        if (expr != null) {
            exprCompileRes = ANTLRCompiler.compile(expr);
        }
        return exprCompileRes;
    }

    public static void main(String[] args) {
        List b = Arrays.asList("OPEN_STORE_NUM");
        Object a = Optional.ofNullable(new ArrayList<>()).orElse(new ArrayList<>());
    }

    private String AUTO_MAPPING_VALUE() {
        String[] pointers = getPointerChain().split("\\.");

        String autoMappingValue = null;
        for (String pointer : pointers) {
            if (autoMappingValue == null ) {
                autoMappingValue = Objects.equals(pointer, PRE_STEP_OUTPUT_POINTER) ? GET_PRE_STEP_OUTPUT() : pointer;
            } else {
                autoMappingValue = CHILD(autoMappingValue, pointer);
            }
        }
        return autoMappingValue;
    }

    private String DEFAULT_VALUE() {
        String defaultValue = this.flowParam.getDefaultValue();

        if (StringUtils.isEmpty(defaultValue)) {
            switch (flowParam.dataType()) {
                case OBJECT: defaultValue = NEW_MAP();break;
                case ARRAY: defaultValue = NEW_LIST();break;
                case NUMBER:
                case STRING:
                case BOOLEAN:
                default:
                    defaultValue = NULL();break;
            }
        }
        return defaultValue;
    }

    private String NEW_MAP() {
        return "new HashMap<>()";
    }

    private String NEW_LIST() {
        return "new ArrayList<>()";
    }

    private String NULL() {
        return "null";
    }

    protected String DATA_TRANSFER(String object) {
        switch (flowParam.dataType()) {
            case STRING:
                return TRANS_TO_STRING(object);
            case NUMBER:
                return TRANS_TO_NUMBER(object);
            case OBJECT:
                return TRANS_TO_OBJECT(object);
            case BOOLEAN:
                return TRANS_TO_BOOLEAN(object);
            case ARRAY:
                return TRANS_TO_ARRAY(object);
            default:
                return null;
        }
    }

    public abstract List<String> javaCode(boolean needAutoMapping);

    public abstract List<DataTypeEnum> acceptDataType();
}
