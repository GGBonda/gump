package org.junhui.gump.flow.core.step;

import org.junhui.gump.flow.core.runner.FlowContext;

public interface StepProcessor<T extends FlowStep> {

    void process(FlowContext context, T step);
}
