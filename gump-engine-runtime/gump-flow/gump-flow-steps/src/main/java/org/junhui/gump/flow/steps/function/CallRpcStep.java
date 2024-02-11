package org.junhui.gump.flow.steps.function;

import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.common.entity.MetadataId;
import org.junhui.gump.flow.steps.AbstractFlowStep;
import org.junhui.gump.flow.core.annotation.StepMetadata;
import org.junhui.gump.flow.steps.processor.CallRpcProcessor;

import java.util.List;

import static org.junhui.gump.flow.steps.constants.FlowTypeConstants.CALL_RPC_STEP_TYPE;

@StepMetadata(
        name = "callRPC环节",
        type = CALL_RPC_STEP_TYPE,
        codes = {
                "@{builder.compileParam(\"input\", step.getInput(), false)}",
                "((CallRpcProcessor)(getProcessor(\""+ CALL_RPC_STEP_TYPE +"\"))).process(context, (CallRpcStep)getStep())",
                "@{builder.nextPush(step.getNext())}"
        },
        imports = {
                CallRpcStep.class,
                CallRpcProcessor.class
        }
)
public class CallRpcStep extends AbstractFlowStep {

    private MetadataId rpcId;

    private List<FlowParam> input;

    public MetadataId getRpcId() {
        return rpcId;
    }

    public void setRpcId(MetadataId rpcId) {
        this.rpcId = rpcId;
    }

    public List<FlowParam> getInput() {
        return input;
    }

    public void setInput(List<FlowParam> input) {
        this.input = input;
    }

    @Override
    public String getType() {
        return CALL_RPC_STEP_TYPE;
    }
}
