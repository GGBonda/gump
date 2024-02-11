package org.junhui.gump.dev.web.log;

import java.io.Serializable;

public class FlowDebugLogDTO implements Serializable {
    private static final long serialVersionUID = -4912747155305473441L;

    private String stepCode;

    private Object output;

    private Long consumeTime;

    public String getStepCode() {
        return stepCode;
    }

    public void setStepCode(String stepCode) {
        this.stepCode = stepCode;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }

    public Long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Long consumeTime) {
        this.consumeTime = consumeTime;
    }
}
