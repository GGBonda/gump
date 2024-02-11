package org.junhui.gump.persistence.base.ram;

import org.apache.commons.collections4.CollectionUtils;
import org.junhui.gump.persistence.client.api.GumpDirectoryNodePersistenceService;
import org.junhui.gump.persistence.client.model.GumpDirectoryNode;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GumpDirectoryNodePersistenceServiceForRAM implements GumpDirectoryNodePersistenceService {

    private static final Map<Long, GumpDirectoryNode> data = new HashMap<>();
    private static final Map<String, Long> dataUniKey = new HashMap<>();

    @Override
    public void save(GumpDirectoryNode node) {
        Long id = node.getId();
        if (id == null) {
            node = GumpDirectoryNode.builder().id(new Date().getTime())
                    .appCode(node.getAppCode()).folder(node.getFolder()).code(node.getCode())
                    .parentId(node.getParentId()).desc(node.getDesc()).type(node.getType()).build();
        }

        String uniKey = uniKey(node);
        if (dataUniKey.containsKey(uniKey) && !dataUniKey.get(uniKey).equals(node.getId())) {
            throw new RuntimeException("duplicate uni key");
        }
        dataUniKey.put(uniKey, node.getId());
        data.put(node.getId(), node);
    }

    private String uniKey(GumpDirectoryNode node) {
        return String.format("%s_%s_%s_%s", node.getAppCode(), node.getType(), node.getParentId(), node.getCode());
    }

    @Override
    public List<GumpDirectoryNode> getList(String appCode, String type, Long parentId) {
        return data.values().stream().filter(
                node -> {
                    if (parentId != null && parentId > 0) {
                        return Objects.equals(appCode, node.getAppCode()) && Objects.equals(type, node.getType()) && Objects.equals(parentId, node.getParentId());
                    } else {
                        return Objects.equals(appCode, node.getAppCode()) && Objects.equals(type, node.getType()) && node.getParentId() == null;
                    }
                }
        ).collect(Collectors.toList());
    }

    @Override
    public GumpDirectoryNode getOne(String appCode, Long nodeId) {
        return data.get(nodeId);
    }

    @Override
    public Long existFolder(String appCode, String code, String type, Long parentId) {
        List<GumpDirectoryNode> targetNodes = data.values().stream().filter(item -> {
            if (parentId == null) {
                return Objects.equals(appCode, item.getAppCode())
                        && Objects.equals(code, item.getCode())
                        && Objects.equals(type, item.getType())
                        && item.getParentId() == null;
            } else {
                return Objects.equals(appCode, item.getAppCode())
                        && Objects.equals(code, item.getCode())
                        && Objects.equals(type, item.getType())
                        && Objects.equals(parentId, item.getParentId());
            }
        }).collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(targetNodes) ? targetNodes.get(0).getId() : null;
    }

    @Override
    public Long existFile(String appCode, String code, String type) {
        List<GumpDirectoryNode> targetNodes = data.values().stream().filter(item ->
                Objects.equals(appCode, item.getAppCode())
                && Objects.equals(code, item.getCode())
                && Objects.equals(type, item.getType())
        ).collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(targetNodes) ? targetNodes.get(0).getId() : null;
    }
}
