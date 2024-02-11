package org.junhui.gump.dev.web.log;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlowDebugLogService {

    private static final Cache<String, List<FlowDebugLogDTO>> CACHE = CacheBuilder.newBuilder()
            .initialCapacity(10)//初始容量
            .maximumSize(1000)//最大容量，超过最大容量即按照LRU最近最少使用算法来移除缓存项
            .build();


    public void setCache(String traceId, List<FlowDebugLogDTO> logDTOList) {
        CACHE.put(traceId, logDTOList);
    }

    public List<FlowDebugLogDTO> get(String traceId) {
        return CACHE.getIfPresent(traceId);
    }

    public void remove(String traceId) {
        CACHE.invalidate(traceId);
    }

}
