package org.junhui.gump.flow.steps.prime;

import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.flow.core.annotation.StepMetadata;
import org.junhui.gump.flow.steps.AbstractFlowStep;
import org.junhui.gump.flow.steps.constants.FlowTypeConstants;

import java.util.List;


@StepMetadata(
        name = "input环节",
        type = FlowTypeConstants.DATA_PROCESSING_STEP_TYPE,
        codes = {
                "@{builder.compileParam(\"params\", step.getInput(), true)}",
                "saveCurrentStepOutput(getCurrentStepValue(\"params\"))",
                "@{builder.nextPush(step.getNext())}"
        }
)
public class DataProcessingStep extends AbstractFlowStep {

    private List<FlowParam> params;

    public List<FlowParam> getParams() {
        return params;
    }

    public void setParams(List<FlowParam> params) {
        this.params = params;
    }

    @Override
    public String getType() {
        return FlowTypeConstants.DATA_PROCESSING_STEP_TYPE;
    }
}
