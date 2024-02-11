package org.junhui.gump.flow.core.runner;

import org.apache.commons.lang3.StringUtils;
import org.junhui.gump.flow.core.entity.StepValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FlowContext {

    /**
     * flow运行入参
     * */
    private final Input input;

    /**
     * flow运行出参
     * */
    private Object output;

    private String preStepCode;

    /**
     * 各个环节运行的结果
     * */
    private final Map<String, Object> stepOutputMap;

    /**
     * 当前环节
     * */
    private StepValue currentStep;

    private final Stack<String> stack;

    public FlowContext(Input input) {
        this.stepOutputMap = new HashMap<>();
        this.stack = new Stack<>();
        this.input = input;
    }

    public void saveCurrentStepOutput(Object output) {
        this.stepOutputMap.put(this.currentStep.getCode(), output);
    }

    void initCurrentStep(String code) {
        this.currentStep = new StepValue(code);
    }

    public void stackPush(String next) {
        if (StringUtils.isEmpty(next)) {
            return;
        }
        if (this.currentStep != null) {
            this.preStepCode = this.currentStep.getCode();
        }

        stack.push(next);
    }

    Object getPreStepOutput() {
        if (StringUtils.isEmpty(getPreStepCode())) {
            return this.getInput().getInput();
        }
        return getStepOutput(getPreStepCode());
    }

    public String getPreStepCode() {
        return preStepCode;
    }

    public Map<String, Object> getStepOutputMap() {
        return stepOutputMap;
    }

    String stackPop() {
        return stack.pop();
    }

    public boolean stackEmpty() {
        return stack.empty();
    }

    void clearStack() {
        this.output = getStepOutput(getCurrentStepCode());
        stack.clear();
    }

    void setCurrentStepValue(String key, Object value) {
        currentStep.put(key, value);
    }

    public Input getInput() {
        return input;
    }

    public Object getOutput() {
        return output;
    }

    public Object getStepOutput(String stepCode) {
        return stepOutputMap.get(stepCode);
    }

    public String getCurrentStepCode() {
        return currentStep.getCode();
    }

    public Object getCurrentStepValue(String key) {
        return currentStep.get(key);
    }
}
