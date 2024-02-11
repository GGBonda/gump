package org.junhui.gump.flow.steps.prime;

import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.flow.core.annotation.StepMetadata;
import org.junhui.gump.flow.steps.ByRoadFlowStep;
import org.junhui.gump.flow.steps.constants.FlowTypeConstants;

@StepMetadata(
        name = "if环节",
        type = FlowTypeConstants.IF_STEP_TYPE,
        codes = {
                "@{builder.compileParam(\"condition\", step.getCondition(), false)}",
                "@{builder.nextPush(step.getNext())}",
                "if (transToBoolean(getCurrentStepValue(\"condition\"))) {",
                    "@{builder.nextPush(step.getByRoadNext())}",
                "}"
        }
)
public class IfStep extends ByRoadFlowStep {

    private FlowParam condition;

    public FlowParam getCondition() {
        return condition;
    }

    public void setCondition(FlowParam condition) {
        this.condition = condition;
    }

    @Override
    public String getType() {
        return FlowTypeConstants.IF_STEP_TYPE;
    }
}
