package org.junhui.gump.flow.client;

import org.junhui.gump.flow.core.runner.RuntimeEnv;

import java.io.Serializable;

public class GumpFlowResponse implements Serializable {
    private static final long serialVersionUID = -1021393424087072969L;

    private String traceId;

    private boolean success;

    private String errorCode;

    private String errorMsg;

    private String errorStepCode;

    private Object result;

    private static final String SUCCESS_CODE = "000000000";

    private static final String DEFAULT_ERROR_CODE = "999999999";

    private GumpFlowResponse() {}

    public static GumpFlowResponse success(Object result) {
        GumpFlowResponse response = new GumpFlowResponse();
        response.success = true;
        response.errorCode = SUCCESS_CODE;
        response.result = result;
        response.traceId = RuntimeEnv.traceId();
        return response;
    }

    public static GumpFlowResponse fail(String errorCode, String errorMsg, String errorStepCode) {
        GumpFlowResponse response = new GumpFlowResponse();
        response.success = false;
        response.errorCode = errorCode;
        response.errorMsg = errorMsg;
        response.errorStepCode = errorStepCode;
        response.traceId = RuntimeEnv.traceId();
        return response;
    }

    public static GumpFlowResponse fail(String errorMsg, String errorStepCode) {
        GumpFlowResponse response = new GumpFlowResponse();
        response.success = false;
        response.errorCode = DEFAULT_ERROR_CODE;
        response.errorMsg = errorMsg;
        response.errorStepCode = errorStepCode;
        response.traceId = RuntimeEnv.traceId();
        return response;
    }

    public String getTraceId() {
        return traceId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getErrorStepCode() {
        return errorStepCode;
    }

    public Object getResult() {
        return result;
    }
}
