package org.junhui.gump.rpc.core.groovy;

import groovy.lang.GroovyObject;
import org.apache.commons.lang3.StringUtils;
import org.junhui.gump.util.GroovyScriptAnalyser;

import java.util.concurrent.ExecutionException;

public class GroovyScriptRunner {

    public Object run(String script, Object rpcResponse) throws ExecutionException {
        if (StringUtils.isEmpty(script)) {
            return rpcResponse;
        }

        GroovyObject groovyObject = GroovyScriptAnalyser.analyse(script);

        RpcInvokeResponseHandler handler = (RpcInvokeResponseHandler) groovyObject;

        RpcResponseContext context = new RpcResponseContext();
        context.setParam(rpcResponse);
        return handler.handle(context);
    }

}
