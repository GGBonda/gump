package org.junhui.gump.flow.poet.compiler;

import com.squareup.javapoet.*;
import org.apache.commons.collections4.CollectionUtils;
import org.junhui.gump.flow.core.annotation.StepMetadata;
import org.junhui.gump.flow.core.compile.java.JavaCodeCompiler;
import org.junhui.gump.flow.core.entity.GumpFlow;
import org.junhui.gump.flow.core.runner.BaseFlowRunner;
import org.junhui.gump.flow.core.runner.FlowContext;
import org.junhui.gump.flow.core.step.FlowStep;
import org.mvel2.templates.TemplateRuntime;

import javax.lang.model.element.Modifier;
import java.util.*;
import java.util.stream.Collectors;

import static org.junhui.gump.flow.core.runner.method_constants.BaseRunnerMethodConstants.*;
import static org.junhui.gump.flow.steps.constants.FlowTypeConstants.INPUT_STEP_TYPE;


public class DefaultJavaCodeCompiler implements JavaCodeCompiler {

    private final FlowStepBuilder flowStepBuilder = new FlowStepBuilder();

    @Override
    public String compile(GumpFlow flow) {
        List<FlowStep> flowSteps = flow.getSteps();
        if (CollectionUtils.isEmpty(flowSteps)) {
            return null;
        }

        MethodSpec runMethod = runMethod(flowSteps);
        List<MethodSpec> stepMethods = flowSteps.stream().map(this::stepMethod).collect(Collectors.toList());

        TypeSpec typeSpec = TypeSpec.classBuilder(flow.getCode())
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .superclass(ClassName.get(BaseFlowRunner.class))
                .addMethod(constructMethod())
                .addMethod(runMethod)
                .addMethods(stepMethods)
                .build();

        JavaFile javaFile = JavaFile.builder(flow.getAppCode(), typeSpec).build();

        return injectImports(javaFile, stepMetadataImports(flowSteps));
    }

    private MethodSpec constructMethod() {
        return MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(FlowContext.class, "context")
                .addParameter(GumpFlow.class, "flow")
                .addStatement("super(context, flow)")
                .build();
    }

    private List<Class> stepMetadataImports(List<FlowStep> flowSteps) {
        List<Class> imports = new ArrayList<>();

        for (FlowStep step : flowSteps) {
            StepMetadata stepMetadata = step.getClass().getAnnotation(StepMetadata.class);
            imports.addAll(Arrays.asList(stepMetadata.imports()));
        }
        return imports;
    }


    private MethodSpec runMethod(List<FlowStep> flowSteps) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("run")
                .addModifiers(Modifier.PUBLIC)
                .returns(void.class);

        List<CodeBlock> caseCodeBlocks = new ArrayList<>(flowSteps.size());
        for (FlowStep step : flowSteps) {
            if (Objects.equals(step.getType(), INPUT_STEP_TYPE)) {
                builder.addStatement(NEXT_PUSH(step.getCode()));
            }
            caseCodeBlocks.add(
                    CodeBlock.builder()
                            .beginControlFlow("case $S:", step.getCode())
                            .addStatement("$L()", stepMethodName(step.getCode()))
                            .addStatement("break")
                            .endControlFlow()
                            .build()
            );
        }

        builder.beginControlFlow(String.format("while (%s)", HAS_NEXT()))
                .addStatement(String.format("String stepCode = %s", NEXT()))
                .addStatement(RUN_STEP_BEFORE())
                .beginControlFlow("switch (stepCode)");

        caseCodeBlocks.forEach(builder::addCode);

        builder.endControlFlow()
                .addStatement(RUN_STEP_AFTER())
                .endControlFlow();
        return builder.build();
    }

    private MethodSpec stepMethod(FlowStep step) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(stepMethodName(step.getCode()))
                .addModifiers(Modifier.PRIVATE)
                .returns(void.class);

        List<String> methodCodes = new ArrayList<>();
        StepMetadata stepMetadata = step.getClass().getAnnotation(StepMetadata.class);
        if (stepMetadata != null && stepMetadata.codes().length > 0) {
            Map<String, Object> mvelContext = new HashMap<>();
            mvelContext.put("builder", flowStepBuilder);
            mvelContext.put("step", step);

            for (String code : stepMetadata.codes()) {
                Object evalRes = TemplateRuntime.eval(code, mvelContext);
                if (evalRes instanceof String) {
                    methodCodes.add((String) evalRes);
                } else if (evalRes instanceof Collection) {
                    methodCodes.addAll((Collection<? extends String>) evalRes);
                }
            }
        }

        methodCodes.forEach(builder::addStatement);
        return builder.build();
    }

    private String injectImports(JavaFile javaFile, List<Class> imports) {
        String[] javaCodes = javaFile.toString().split("\n", -1);

        List<String> result = new ArrayList<>();

        for (String code : javaCodes) {
            result.add(code);

            if (code.startsWith("package ")) {
                result.add("");
                result.add("import java.util.*;");
                result.add("import java.util.stream.*;");
                for (Class i : imports) {
                    result.add("import " + i.getName() + ";");
                }
            }
        }
        return String.join("\n", result);
    }



    private String stepMethodName(String code) {
        return String.format("run%s", firstLetterUpperCase(code));
    }

    private String firstLetterUpperCase(String str) {
        char[] charArray = str.toCharArray();
        if (charArray[0] >= 'a' && charArray[0] <= 'z') {
            charArray[0] = (char) (charArray[0] - 32);
        }
        return new String(charArray);
    }
}
