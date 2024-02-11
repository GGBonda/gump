package org.junhui.gump.flow.core.interceptor;

import org.junhui.gump.flow.core.runner.FlowContext;

public interface FlowStepInterceptor {

    void before(FlowContext context);

    void after(FlowContext context);
}
