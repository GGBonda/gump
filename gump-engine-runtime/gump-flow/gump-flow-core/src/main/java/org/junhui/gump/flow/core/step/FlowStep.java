package org.junhui.gump.flow.core.step;

public interface FlowStep {

    String getCode();

    /**
     * 必须复写getType方法
     * */
    String getType();

}
