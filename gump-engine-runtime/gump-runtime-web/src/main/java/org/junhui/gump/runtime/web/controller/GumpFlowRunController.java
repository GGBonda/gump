package org.junhui.gump.runtime.web.controller;

import org.junhui.gump.flow.client.GumpFlowClient;
import org.junhui.gump.flow.client.GumpFlowRequest;
import org.junhui.gump.flow.core.entity.GumpFlow;
import org.junhui.gump.flow.core.runner.RuntimeEnv;
import org.junhui.gump.flow.core.step.FlowStep;
import org.junhui.gump.flow.steps.FlowStepDeserializer;
import org.junhui.gump.persistence.client.GumpMetadataTypeEnums;
import org.junhui.gump.persistence.client.api.GumpMetadataPersistenceService;
import org.junhui.gump.persistence.client.model.GumpMetadata;
import org.junhui.gump.util.GsonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/flow")
public class GumpFlowRunController {

    @Autowired
    private GumpMetadataPersistenceService metadataPersistenceService;

    private final GumpFlowClient gumpFlowClient = new GumpFlowClient();

    @PostMapping(value = "run", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Object run(@RequestBody Map<String, Object> param) {
        String appCode = (String) param.get("appCode");
        String code = (String) param.get("flowCode");
        String input = (String) param.get("input");

        GumpMetadata metadata = metadataPersistenceService.getUnique(appCode, code, GumpMetadataTypeEnums.FLOW.getCode());

        RuntimeEnv.init();

        GumpFlowRequest request = new GumpFlowRequest();
        request.setInput(GsonManager.getInstance().parseObject(input, HashMap.class));

        return RuntimeEnv.runInScope(req -> gumpFlowClient.run(transfer(metadata), req), request);
    }

    private GumpFlow transfer(GumpMetadata metadata) {
        GumpFlow flow = GsonManager.getInstance()
                .registerTypeAdapter(FlowStep.class, new FlowStepDeserializer())
                .parseObject(metadata.getContent(), GumpFlow.class);
        flow.setAppCode(metadata.getAppCode());
        flow.setCode(metadata.getCode());
        flow.setDesc(metadata.getDesc());
        return flow;
    }
}
