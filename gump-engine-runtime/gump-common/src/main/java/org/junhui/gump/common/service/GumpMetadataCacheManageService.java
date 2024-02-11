package org.junhui.gump.common.service;

import org.junhui.gump.common.entity.BaseMetadata;
import org.junhui.gump.common.entity.MetadataId;

import java.util.concurrent.ExecutionException;

public interface GumpMetadataCacheManageService {

    <T extends BaseMetadata> T getMetadata(MetadataId id, Class<T> tClass);

    void removeCache(MetadataId id);

    void clear();
}
