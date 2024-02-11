package org.junhui.gump.flow.core.runner.method_constants;

public class BaseRunnerMethodConstants {

    public static String ALL_STEP_OUTPUT() {
        return "super.getStepsOutput()";
    }

    public static String HAS_NEXT() {
        return "super.hasNext()";
    }

    public static String NEXT() {
        return "super.next()";
    }

    public static String NEXT_PUSH(String nextStepCode) {
        return String.format("super.nextPush(\"%s\")", nextStepCode);
    }

    public static String RUN_STEP_BEFORE() {
        return "super.stepRunBefore()";
    }

    public static String RUN_STEP_AFTER() {
        return "super.stepRunAfter()";
    }

    public static String SET_CURRENT_STEP_VALUE(String key, String valuePointer) {
        return String.format("super.setCurrentStepValue(\"%s\", %s)", key, valuePointer);
    }

    public static String GET_PRE_STEP_OUTPUT() {
        return "super.getPreStepOutput()";
    }

}
