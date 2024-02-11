package org.junhui.gump.dev.web.controller;

import org.junhui.gump.common.entity.MetadataId;
import org.junhui.gump.common.service.GumpMetadataCacheManageService;
import org.junhui.gump.persistence.client.api.GumpMetadataPersistenceService;
import org.junhui.gump.persistence.client.model.GumpMetadata;
import org.junhui.gump.util.GsonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/metadata")
public class MetadataManageController {

    @Autowired
    private GumpMetadataCacheManageService metadataCacheManage;

    @Autowired
    private GumpMetadataPersistenceService metadataPersistenceService;

    @PostMapping(value = "save", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Boolean saveMetadata(@RequestBody Map<String, Object> param) {
        Long nodeId = (Long) param.get("nodeId");
        String code = (String) param.get("code");
        String desc = (String) param.get("desc");
        String type = (String) param.get("type");
        String appCode = (String) param.get("appCode");

        param.remove("nodeId");
        param.remove("code");
        param.remove("desc");
        param.remove("type");
        param.remove("appCode");

        GumpMetadata metadata = GumpMetadata.builder().nodeId(nodeId)
                .code(code).desc(desc).type(type).appCode(appCode).content(GsonManager.getInstance().toJSONString(param))
                .build();
        metadataPersistenceService.save(metadata);
        metadataCacheManage.removeCache(new MetadataId(nodeId));
        return true;
    }

    @PostMapping(value = "queryMetadataDetailByNodeId", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Object queryMetadataDetailByNodeId(@RequestBody Map<String, Object> param) {
        String nodeId = param.get("nodeId").toString();

        GumpMetadata metadata = metadataPersistenceService.getUnique(Long.valueOf(nodeId));
        if (metadata == null) {
            return null;
        }

        Map<String, Object> map = GsonManager.getInstance().parseObject(metadata.getContent(), HashMap.class);
        map.put("code", metadata.getCode());
        map.put("type", metadata.getType());
        map.put("desc", metadata.getDesc());
        map.put("appCode", metadata.getAppCode());
        map.put("nodeId", metadata.getNodeId());

        return map;
    }

    @PostMapping(value = "queryMetadataList", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Object queryMetadataList(@RequestBody Map<String, Object> param) {
        String appCode = (String) param.get("appCode");
        String type = (String) param.get("type");

        return metadataPersistenceService.getList(appCode, type);
    }

}
