package org.junhui.gump.flow.steps.processor;

import org.junhui.gump.common.service.GumpMetadataCacheManageService;
import org.junhui.gump.flow.core.annotation.StepProcessorBean;
import org.junhui.gump.flow.core.runner.FlowContext;
import org.junhui.gump.flow.core.step.StepProcessor;
import org.junhui.gump.flow.steps.constants.FlowTypeConstants;
import org.junhui.gump.flow.steps.function.CallRpcStep;
import org.junhui.gump.rpc.client.GumpRpcClient;
import org.junhui.gump.rpc.core.entity.GumpRpc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutionException;


@StepProcessorBean(stepCode = FlowTypeConstants.CALL_RPC_STEP_TYPE)
public class CallRpcProcessor implements StepProcessor<CallRpcStep> {

    @Autowired
    private GumpMetadataCacheManageService gumpMetadataCacheManage;

    @Autowired
    private GumpRpcClient rpcClient;

    @Override
    public void process(FlowContext context, CallRpcStep step) {
        GumpRpc rpc = gumpMetadataCacheManage.getMetadata(step.getRpcId(), GumpRpc.class);
        if (rpc == null) {
            return;
        }

        try {
            Object res = rpcClient.run(rpc, context.getCurrentStepValue("input"));
            context.saveCurrentStepOutput(res);
        } catch (ExecutionException e) {
            context.saveCurrentStepOutput(null);
        }
    }
}
