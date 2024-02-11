package org.junhui.gump.flow.steps;

import org.junhui.gump.flow.core.step.FlowStep;

public abstract class AbstractFlowStep implements FlowStep {

    private String code;

    /**
     * 注释
     * */
    private String comment;

    private String next;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
