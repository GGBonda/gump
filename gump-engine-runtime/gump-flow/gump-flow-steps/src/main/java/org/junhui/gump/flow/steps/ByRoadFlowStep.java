package org.junhui.gump.flow.steps;

/**
 * 拥有分支的环节
 * */
public abstract class ByRoadFlowStep extends AbstractFlowStep {

    /**
     * 分支下一步
     * */
    private String byRoadNext;

    public String getByRoadNext() {
        return byRoadNext;
    }

    public void setByRoadNext(String byRoadNext) {
        this.byRoadNext = byRoadNext;
    }
}
