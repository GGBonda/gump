package org.junhui.gump.dev.web.controller;

import org.junhui.gump.persistence.client.GumpMetadataTypeEnums;
import org.junhui.gump.persistence.client.api.GumpMetadataPersistenceService;
import org.junhui.gump.persistence.client.model.GumpMetadata;
import org.junhui.gump.rpc.client.GumpRpcClient;
import org.junhui.gump.rpc.core.entity.GumpRpc;
import org.junhui.gump.util.GsonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/rpc")
public class GumpRpcManageController {

    @Autowired
    private GumpRpcClient rpcClient;

    @Autowired
    private GumpMetadataPersistenceService metadataPersistenceService;

    @PostMapping(value = "debug", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Object debug(@RequestBody Map<String, Object> param) throws ExecutionException {
        String appCode = (String) param.get("appCode");
        String code = (String) param.get("rpcCode");
        String input = (String) param.get("input");

        GumpMetadata metadata = metadataPersistenceService.getUnique(appCode, code, GumpMetadataTypeEnums.RPC.getCode());

        return rpcClient.run(transfer(metadata), GsonManager.getInstance().parseObject(input, HashMap.class));
    }

    private GumpRpc transfer(GumpMetadata metadata) {
        GumpRpc rpc = GsonManager.getInstance().parseObject(metadata.getContent(), GumpRpc.class);
        rpc.setAppCode(metadata.getAppCode());
        rpc.setCode(metadata.getCode());
        rpc.setDesc(metadata.getDesc());
        return rpc;
    }
}
