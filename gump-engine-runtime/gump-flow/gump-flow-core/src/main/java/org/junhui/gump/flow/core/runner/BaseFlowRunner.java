package org.junhui.gump.flow.core.runner;

import org.junhui.gump.flow.core.entity.GumpFlow;
import org.junhui.gump.flow.core.functions.GumpFunctionManager;
import org.junhui.gump.flow.core.interceptor.FlowStepInterceptorManager;
import org.junhui.gump.flow.core.step.FlowStep;
import org.junhui.gump.flow.core.step.StepProcessor;
import org.junhui.gump.flow.core.step.StepProcessorBeanManager;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseFlowRunner implements DataTranslator, CalculateOperation, CompareOperation {
    protected final FlowContext context;
    private final Map<String, FlowStep> stepMap;
    private final GumpFunctionManager gumpFunctionManager;
    private final StepProcessorBeanManager stepProcessorBeanManager;
    private final FlowStepInterceptorManager flowStepInterceptorManager;

    protected BaseFlowRunner(FlowContext context, GumpFlow flow) {
        this.context = context;
        this.stepMap = flow.getSteps().stream().collect(Collectors.toMap(FlowStep::getCode, v -> v));
        this.gumpFunctionManager = GumpFunctionManager.getInstance();
        this.stepProcessorBeanManager = StepProcessorBeanManager.getInstance();
        this.flowStepInterceptorManager = FlowStepInterceptorManager.getInstance();
    }

    public abstract void run();

    protected void stepRunBefore() {
        this.flowStepInterceptorManager.before(context);
    }

    protected void stepRunAfter() {
        this.flowStepInterceptorManager.after(context);
    }

    protected boolean hasNext() {
        return !context.stackEmpty();
    }

    protected String next() {
        String nextStepCode = context.stackPop();
        initCurrentStep(nextStepCode);
        return nextStepCode;
    }

    protected FlowStep getStep() {
        return this.stepMap.get(context.getCurrentStepCode());
    }

    protected void nextPush(String next) {
        context.stackPush(next);
    }

    protected void setCurrentStepValue(String key, Object value) {
        context.setCurrentStepValue(key, value);
    }

    protected Object getCurrentStepValue(String key) {
        return context.getCurrentStepValue(key);
    }

    protected void saveCurrentStepOutput(Object output) {
        context.saveCurrentStepOutput(output);
    }

    protected Object getStepOutput(String stepCode) {
        return context.getStepOutput(stepCode);
    }

    protected Object getStepsOutput() {
        return context.getStepOutputMap();
    }

    private void initCurrentStep(String code) {
        context.initCurrentStep(code);
    }

    protected Object getPreStepOutput() {
        return context.getPreStepOutput();
    }

    protected void clearStack() {
        context.clearStack();
    }

    protected Object gfRun(String funcCode, Object... params) {
        try {
            return gumpFunctionManager.invoke(funcCode, params);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected StepProcessor<?> getProcessor(String stepType) {
        return stepProcessorBeanManager.getProcessor(stepType);
    }
}
