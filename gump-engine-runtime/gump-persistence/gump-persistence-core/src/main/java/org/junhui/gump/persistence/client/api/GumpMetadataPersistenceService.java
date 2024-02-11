package org.junhui.gump.persistence.client.api;

import org.junhui.gump.persistence.client.model.GumpMetadata;

import java.util.List;

public interface GumpMetadataPersistenceService {

    void save(GumpMetadata metadata);

    GumpMetadata getUnique(String appCode, String code, String type);

    List<GumpMetadata> getList(String appCode, String type);

    GumpMetadata getUnique(Long nodeId);
}
