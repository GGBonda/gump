package org.junhui.gump.flow.steps.processor;

import org.apache.commons.collections4.CollectionUtils;
import org.junhui.gump.flow.core.annotation.StepProcessorBean;
import org.junhui.gump.flow.core.runner.FlowContext;
import org.junhui.gump.flow.core.step.StepProcessor;
import org.junhui.gump.flow.steps.constants.FlowTypeConstants;
import org.junhui.gump.flow.steps.prime.ForStep;

import java.util.*;

@StepProcessorBean(stepCode = FlowTypeConstants.FOR_STEP_TYPE)
public class ForStepProcessor implements StepProcessor<ForStep> {

    @Override
    public void process(FlowContext context, ForStep step) {
        Object cycleParam = context.getCurrentStepValue("cycleParam");
        if (cycleParam == null) {
            return;
        }

        Map<String, Object> forOutput = (Map<String, Object>) context.getStepOutput(context.getCurrentStepCode());
        if (forOutput == null) {
            forOutput = new HashMap<>();
        } else {
            appendResult(forOutput, context.getStepOutput(context.getPreStepCode()));
        }

        if (cycleParam instanceof Integer) {
            cycleByTimes((Integer)cycleParam, forOutput);
        } else
        if (cycleParam instanceof List) {
            cycleByParams((List<Object>) cycleParam, step.getStep(), forOutput);
        }

        if (ended(forOutput)) {
            context.stackPush(step.getNext());
        } else {
            context.stackPush(step.getCode());
            context.stackPush(step.getByRoadNext());
        }

        context.saveCurrentStepOutput(forOutput);
    }

    private void cycleByTimes(Integer times, Map<String, Object> forOutput) {
        if (times == null || times <= index(forOutput)) {
            end(forOutput);
        } else {
            startNextCycle(forOutput, Collections.emptyList());
        }
    }

    private void cycleByParams(List<Object> params, Integer step, Map<String, Object> forOutput) {
        if (CollectionUtils.isEmpty(params)) {
            end(forOutput);
        }

        int times = params.size()/step + (params.size() % step > 0 ? 1 : 0);
        if (times <= index(forOutput)) {
            end(forOutput);
        } else {
            int start = index(forOutput) * step;
            int end = Math.min(start + step, params.size());
            startNextCycle(forOutput, params.subList(start, end));
        }
    }

    private int index(Map<String, Object> forOutput) {
        Integer index = (Integer) forOutput.get("index");
        return index == null ? 0 : index;
    }

    private void end(Map<String, Object> forOutput) {
        forOutput.put("index", -1);
        forOutput.put("items", Collections.emptyList());
    }

    private boolean ended(Map<String, Object> forOutput) {
        Integer index = (Integer) forOutput.get("index");
        return index == null || index < 0;
    }

    private void startNextCycle(Map<String, Object> forOutput, List<Object> items) {
        Integer index = (Integer) forOutput.get("index");
        forOutput.put("index", index == null ? 1 : index + 1);
        forOutput.put("items", items);
    }

    private void appendResult(Map<String, Object> forOutput, Object result) {
        List<Object> results = (List<Object>) forOutput.get("results");
        if (results == null) {
            results = new ArrayList<>();
        }
        results.add(result);
        forOutput.put("results", results);
    }
}
