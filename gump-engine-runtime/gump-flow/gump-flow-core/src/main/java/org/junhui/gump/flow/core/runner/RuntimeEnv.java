package org.junhui.gump.flow.core.runner;


import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.UUID;
import java.util.function.Function;

public class RuntimeEnv {

    private static final ThreadLocal<RuntimeEnv> ENV_THREAD_LOCAL = new TransmittableThreadLocal<>();

    public static void init() {
        if (ENV_THREAD_LOCAL.get() == null) {
            ENV_THREAD_LOCAL.set(new RuntimeEnv());
        }
    }

    public static <T, R> R runInScope(Function<T, R> function, T request) {
        try {
            return function.apply(request);
        } finally {
            ENV_THREAD_LOCAL.remove();
        }
    }

    public static String traceId() {
        return ENV_THREAD_LOCAL.get() == null ? null : ENV_THREAD_LOCAL.get().traceId;
    }

    private RuntimeEnv() {
        this.traceId = UUID.randomUUID().toString().replace("-", "");
    }

    private final String traceId;

    //private String tenantCode;


}
