package org.junhui.gump.persistence.client.api;


import org.junhui.gump.persistence.client.model.GumpDirectoryNode;

import java.util.List;

public interface GumpDirectoryNodePersistenceService {

    void save(GumpDirectoryNode node);

    List<GumpDirectoryNode> getList(String appCode, String type, Long parentId);

    GumpDirectoryNode getOne(String appCode, Long nodeId);

    Long existFolder(String appCode, String code, String type, Long parentId);

    Long existFile(String appCode, String code, String type);
}
