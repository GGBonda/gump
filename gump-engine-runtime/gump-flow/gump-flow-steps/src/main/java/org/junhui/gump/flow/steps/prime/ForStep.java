package org.junhui.gump.flow.steps.prime;

import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.flow.core.annotation.StepMetadata;
import org.junhui.gump.flow.steps.ByRoadFlowStep;
import org.junhui.gump.flow.steps.processor.ForStepProcessor;

import static org.junhui.gump.flow.steps.constants.FlowTypeConstants.FOR_STEP_TYPE;

@StepMetadata(
        name = "for循环环节",
        type = FOR_STEP_TYPE,
        imports = {
                ForStep.class,
                ForStepProcessor.class
        },
        codes = {
                "@{builder.compileParam(\"cycleParam\", step.getCycleParam(), false)}",
                "((ForStepProcessor)(getProcessor(\""+ FOR_STEP_TYPE +"\"))).process(context, (ForStep)getStep())"
        }
)
public class ForStep extends ByRoadFlowStep {

    private FlowParam cycleParam;

    private Integer step;

    public Integer getStep() {
        return step != null && step > 0 ? step : 1;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public FlowParam getCycleParam() {
        return cycleParam;
    }

    public void setCycleParam(FlowParam cycleParam) {
        this.cycleParam = cycleParam;
    }


    @Override
    public String getType() {
        return FOR_STEP_TYPE;
    }
}
