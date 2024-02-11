package org.junhui.gump.rpc.dubbo;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.google.common.base.Objects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junhui.gump.rpc.core.service.InvokeRequest;
import org.junhui.gump.rpc.core.service.RpcInvoker;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class DubboRpcInvoker implements RpcInvoker, InitializingBean {

    private LoadingCache<CacheKey, GenericService> GENERIC_SERVICE_CACHE;

    @Value("${dubbo.registry.address:zookeeper://127.0.0.1:2181}")
    private String registryAddress;

    @Value("${dubbo.invoke.timeout:1000}")
    private Integer invokeTimeout;

    @Override
    public Object invoke(InvokeRequest request) {
        GenericService genericService = this.getGenericService(request);
        if (genericService == null) {
            return null;
        }

        try {
            return genericService.$invoke(request.getMethod(), request.getParamsClassNames(), request.getParams());
        } catch (RpcException e) {

            return null;
        }
    }

    private GenericService getGenericService(InvokeRequest request) {
        try {
            return GENERIC_SERVICE_CACHE.get(new CacheKey(request.getApplication(), request.getService(), request.getTargetProviderIP()));
        } catch (ExecutionException e) {

            return null;
        }
    }

    @Override
    public void afterPropertiesSet() {
        GENERIC_SERVICE_CACHE = CacheBuilder.newBuilder()
                .initialCapacity(10)//初始容量
                .maximumSize(2000)//最大容量，超过最大容量即按照LRU最近最少使用算法来移除缓存项
                .build(
                    new CacheLoader<CacheKey, GenericService>() {
                        @Override
                        public GenericService load(CacheKey key) throws Exception {
                            RegistryConfig registryConfig = new RegistryConfig();
                            registryConfig.setAddress(registryAddress);

                            ApplicationConfig applicationConfig = new ApplicationConfig();
                            applicationConfig.setName(key.getApplication());
                            applicationConfig.setRegistry(registryConfig);

                            ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
                            referenceConfig.setApplication(applicationConfig);
                            referenceConfig.setInterface(key.getService());
                            referenceConfig.setGeneric(true);
                            referenceConfig.setUrl(key.getTargetProviderIP());
                            referenceConfig.setTimeout(invokeTimeout);

                            return referenceConfig.get();
                        }
                    }
                );
    }

    private static class CacheKey {

        private final String application;

        private final String service;

        private final String targetProviderIP;

        public CacheKey(String application, String service, String targetProviderIP) {
            this.application = application;
            this.service = service;
            this.targetProviderIP = targetProviderIP;
        }

        public String getApplication() {
            return application;
        }

        public String getService() {
            return service;
        }

        public String getTargetProviderIP() {
            return targetProviderIP;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CacheKey)) return false;
            CacheKey cacheKey = (CacheKey) o;
            return Objects.equal(application, cacheKey.application) && Objects.equal(service, cacheKey.service) && Objects.equal(targetProviderIP, cacheKey.targetProviderIP);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(application, service, targetProviderIP);
        }
    }
}
