package org.junhui.gump.flow.steps.prime;

import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.flow.steps.AbstractFlowStep;
import org.junhui.gump.flow.core.annotation.StepMetadata;
import org.junhui.gump.flow.steps.constants.FlowTypeConstants;

import java.util.List;


@StepMetadata(
        name = "input环节",
        type = FlowTypeConstants.INPUT_STEP_TYPE,
        codes = {
                "@{builder.compileParam(\"input\", step.getInput(), true)}",
                "super.saveCurrentStepOutput(getCurrentStepValue(\"input\"))",
                "@{builder.nextPush(step.getNext())}"
        }
)
public class InputStep extends AbstractFlowStep {

    private List<FlowParam> input;

    public List<FlowParam> getInput() {
        return input;
    }

    public void setInput(List<FlowParam> input) {
        this.input = input;
    }

    @Override
    public String getType() {
        return FlowTypeConstants.INPUT_STEP_TYPE;
    }
}
