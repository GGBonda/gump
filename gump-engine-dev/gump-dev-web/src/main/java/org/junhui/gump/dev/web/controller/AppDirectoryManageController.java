package org.junhui.gump.dev.web.controller;

import org.junhui.gump.persistence.client.api.GumpDirectoryNodePersistenceService;
import org.junhui.gump.persistence.client.model.GumpDirectoryNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/directory")
public class AppDirectoryManageController {

    @Autowired
    private GumpDirectoryNodePersistenceService directoryNodePersistenceService;

    @PostMapping(value = "saveDirectoryNode", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Boolean saveDirectoryNode(@RequestBody Map<String, Object> param) {
        Long id = (Long) param.get("id");
        String appCode = (String) param.get("appCode");
        Boolean folder = (Boolean) param.get("folder");
        String code = (String) param.get("code");
        Long parentId = (Long) param.get("parentId");
        String desc = (String) param.get("desc");
        String type = (String) param.get("type");

        GumpDirectoryNode node = GumpDirectoryNode.builder()
                .id(id)
                .appCode(appCode).folder(folder).code(code)
                .parentId(parentId).desc(desc).type(type)
                .build();
        directoryNodePersistenceService.save(node);
        return true;
    }


    @PostMapping(value = "queryChildDirectoryNodes", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Object queryChildDirectoryNodes(@RequestBody Map<String, Object> param) {
        String appCode = (String) param.get("appCode");
        String type = (String) param.get("type");
        Long parentId = (Long) param.get("parentId");

        return directoryNodePersistenceService.getList(appCode, type, parentId);
    }

    @PostMapping(value = "queryDirectoryNodeDetail", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Object queryDirectoryNodeDetail(@RequestBody Map<String, Object> param) {
        String appCode = (String) param.get("appCode");
        Long nodeId = (Long) param.get("nodeId");

        return directoryNodePersistenceService.getOne(appCode, nodeId);
    }

    @PostMapping(value = "validateUniqueCode", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Object validateUniqueCode(@RequestBody Map<String, Object> param) {
        String appCode = (String) param.get("appCode");
        String type = (String) param.get("type");
        Long parentId = (Long) param.get("parentId");
        Boolean folder = (Boolean) param.get("folder");
        String code = (String) param.get("code");

        if (folder) {
            return directoryNodePersistenceService.existFolder(appCode, code, type, parentId);
        } else {
            return directoryNodePersistenceService.existFile(appCode, code, type);
        }
    }
}
