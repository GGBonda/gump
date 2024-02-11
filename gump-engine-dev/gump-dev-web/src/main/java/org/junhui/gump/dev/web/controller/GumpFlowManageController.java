package org.junhui.gump.dev.web.controller;

import org.junhui.gump.dev.web.log.FlowDebugLogService;
import org.junhui.gump.flow.client.GumpFlowClient;
import org.junhui.gump.flow.client.GumpFlowRequest;
import org.junhui.gump.flow.client.GumpFlowResponse;
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
public class GumpFlowManageController {

    @Autowired
    private GumpMetadataPersistenceService metadataPersistenceService;

    @Autowired
    private FlowDebugLogService flowDebugLogService;

    private final GumpFlowClient gumpFlowClient = new GumpFlowClient();

    @PostMapping(value = "debug", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Object debug(@RequestBody Map<String, Object> param) {
        String appCode = (String) param.get("appCode");
        String code = (String) param.get("flowCode");
        String input = (String) param.get("input");

        GumpMetadata metadata = metadataPersistenceService.getUnique(appCode, code, GumpMetadataTypeEnums.FLOW.getCode());

        RuntimeEnv.init();

        GumpFlowRequest request = new GumpFlowRequest();
        request.setInput(GsonManager.getInstance().parseObject(input, HashMap.class));

        GumpFlowResponse response = RuntimeEnv.runInScope(req -> gumpFlowClient.run(transfer(metadata), req), request);
        return response;

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

    @PostMapping(value = "debugLog", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Object debugLog(@RequestBody Map<String, Object> param) {
        String traceId = (String) param.get("traceId");

        try {
            return flowDebugLogService.get(traceId);
        } finally {
            flowDebugLogService.remove(traceId);
        }
    }
}
