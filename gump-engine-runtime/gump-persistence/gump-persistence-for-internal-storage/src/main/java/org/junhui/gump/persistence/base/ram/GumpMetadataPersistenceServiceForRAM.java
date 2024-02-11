package org.junhui.gump.persistence.base.ram;

import org.apache.commons.collections4.CollectionUtils;
import org.junhui.gump.persistence.client.api.GumpMetadataPersistenceService;
import org.junhui.gump.persistence.client.model.GumpMetadata;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GumpMetadataPersistenceServiceForRAM implements GumpMetadataPersistenceService {

    private static final Map<Long, GumpMetadata> data = new HashMap<>();

    @Override
    public void save(GumpMetadata metadata) {
        data.put(metadata.getNodeId(), metadata);
    }

    @Override
    public GumpMetadata getUnique(String appCode, String code, String type) {
        List<GumpMetadata> metadataList = data.values().stream().filter(gumpMetadata ->
                Objects.equals(gumpMetadata.getAppCode(), appCode)
                        && Objects.equals(gumpMetadata.getCode(), code)
                        && Objects.equals(gumpMetadata.getType(), type)).collect(Collectors.toList());
        return CollectionUtils.isNotEmpty(metadataList) ? metadataList.get(0) : null;
    }

    @Override
    public List<GumpMetadata> getList(String appCode, String type) {
        return data.values().stream().filter(gumpMetadata ->
                Objects.equals(gumpMetadata.getAppCode(), appCode) && Objects.equals(gumpMetadata.getType(), type)
        ).collect(Collectors.toList());
    }

    @Override
    public GumpMetadata getUnique(Long nodeId) {
        return data.get(nodeId);
    }

}
