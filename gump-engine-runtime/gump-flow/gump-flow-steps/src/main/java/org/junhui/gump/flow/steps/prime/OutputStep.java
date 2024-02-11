package org.junhui.gump.flow.steps.prime;

import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.flow.steps.AbstractFlowStep;
import org.junhui.gump.flow.core.annotation.StepMetadata;
import org.junhui.gump.flow.steps.constants.FlowTypeConstants;

import java.util.List;

@StepMetadata(
        name = "output环节",
        type = FlowTypeConstants.OUTPUT_STEP_TYPE,
        codes = {
                "@{builder.compileParam(\"output\", step.getOutput(), false)}",
                "saveCurrentStepOutput(getCurrentStepValue(\"output\"))",
                "clearStack()"
        }
)
public class OutputStep extends AbstractFlowStep {

    private List<FlowParam> output;

    public List<FlowParam> getOutput() {
        return output;
    }

    public void setOutput(List<FlowParam> output) {
        this.output = output;
    }

    @Override
    public String getType() {
        return FlowTypeConstants.OUTPUT_STEP_TYPE;
    }
}
