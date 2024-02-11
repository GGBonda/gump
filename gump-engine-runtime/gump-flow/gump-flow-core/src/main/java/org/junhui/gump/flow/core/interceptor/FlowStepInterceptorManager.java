package org.junhui.gump.flow.core.interceptor;

import org.apache.commons.collections4.MapUtils;
import org.junhui.gump.flow.core.runner.FlowContext;
import org.junhui.gump.util.SpringContextUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FlowStepInterceptorManager {

    private static FlowStepInterceptorManager singleton;

    private final List<FlowStepInterceptor> interceptorList;

    private FlowStepInterceptorManager() {
        Map<String, FlowStepInterceptor> map = SpringContextUtil.getBeansOfType(FlowStepInterceptor.class);

        if (MapUtils.isEmpty(map)) {
            this.interceptorList = Collections.emptyList();
        } else {
            this.interceptorList = new ArrayList<>(map.values());
        }
    }

    public static FlowStepInterceptorManager getInstance() {
        if (singleton == null) {
            synchronized (FlowStepInterceptorManager.class) {
                if (singleton == null) {
                    singleton = new FlowStepInterceptorManager();
                }
            }
        }
        return singleton;
    }

    public void before(FlowContext context) {
        for (FlowStepInterceptor interceptor : this.interceptorList) {
            interceptor.before(context);
        }
    }

    public void after(FlowContext context) {
        for (FlowStepInterceptor interceptor : this.interceptorList) {
            interceptor.after(context);
        }
    }
}
