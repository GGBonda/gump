package org.junhui.gump.persistence.client;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junhui.gump.common.entity.BaseMetadata;
import org.junhui.gump.common.entity.MetadataId;
import org.junhui.gump.common.service.GumpMetadataCacheManageService;
import org.junhui.gump.persistence.client.api.GumpMetadataPersistenceService;
import org.junhui.gump.persistence.client.model.GumpMetadata;
import org.junhui.gump.util.CacheBreakdownProtection;
import org.junhui.gump.util.GsonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class GumpMetadataCacheManageServiceImpl implements GumpMetadataCacheManageService {

    private final CacheBreakdownProtection cacheBreakdownProtection = new CacheBreakdownProtection();

    private static final Cache<MetadataId, BaseMetadata> METADATA_CACHE = CacheBuilder.newBuilder()
            .initialCapacity(10)//初始容量
            .maximumSize(2000)//最大容量，超过最大容量即按照LRU最近最少使用算法来移除缓存项
            .build();

    @Autowired
    private GumpMetadataPersistenceService persistenceService;

    @Override
    public <T extends BaseMetadata> T getMetadata(MetadataId id, Class<T> tClass) {
        try {
            BaseMetadata metadata = METADATA_CACHE.get(id, () -> {
                Long nodeId = id.getId();
                return cacheBreakdownProtection.protect(nodeId.toString(), 1000, () -> {
                    GumpMetadata gumpMetadata = persistenceService.getUnique(nodeId);
                    if (gumpMetadata == null) {
                        return null;
                    }
                    return GsonManager.getInstance().parseObject(gumpMetadata.getContent(), tClass);
                });
            });
            return (T) metadata;
        } catch (ExecutionException e) {
            return null;
        }
    }

    @Override
    public void removeCache(MetadataId id) {
        METADATA_CACHE.invalidate(id);
    }

    @Override
    public void clear() {
        METADATA_CACHE.invalidateAll();
    }
}
