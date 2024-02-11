package org.junhui.gump.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.concurrent.ExecutionException;

public class GroovyScriptAnalyser {

    private static final Cache<String, GroovyObject> GROOVY_OBJECT_CACHE = CacheBuilder.newBuilder()
            .initialCapacity(10)//初始容量
            .maximumSize(2000)//最大容量，超过最大容量即按照LRU最近最少使用算法来移除缓存项
            .build();

    public static GroovyObject analyse(String script) throws ExecutionException {
        String digest = DigestUtils.md5Hex(script);

        return GROOVY_OBJECT_CACHE.get(digest, () -> {
            GroovyClassLoader loader = new GroovyClassLoader();
            Class<?> scriptClass = loader.parseClass(script);
            return (GroovyObject) scriptClass.getDeclaredConstructor().newInstance();
        });
    }
}
