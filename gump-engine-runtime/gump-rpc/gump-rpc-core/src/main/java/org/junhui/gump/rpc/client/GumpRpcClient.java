package org.junhui.gump.rpc.client;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junhui.gump.common.entity.FlowParam;
import org.junhui.gump.rpc.core.entity.GumpRpc;
import org.junhui.gump.rpc.core.groovy.GroovyScriptRunner;
import org.junhui.gump.rpc.core.service.InvokeRequest;
import org.junhui.gump.rpc.core.service.RpcInvoker;
import org.mvel2.templates.TemplateRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class GumpRpcClient {

    @Autowired
    private RpcInvoker rpcInvoker;

    public Object run(GumpRpc rpc, Object input) throws ExecutionException {
        InvokeRequest request = new InvokeRequest();
        request.setApplication(rpc.getApplication());
        request.setService(rpc.getService());
        request.setMethod(rpc.getMethod());
        request.setParams(new Object[]{input});

        if (CollectionUtils.isNotEmpty(rpc.getInput())) {
            List<FlowParam> params = rpc.getInput();
            params.stream().filter(param -> Objects.equals(param.getCode(), "className")).findFirst().ifPresent(param ->
                request.setParamsClassNames(new String[]{param.getValue()})
            );
        }

        Object response = rpcInvoker.invoke(request);

        //脚本预处理返回参数
        GroovyScriptRunner groovyScriptRunner = new GroovyScriptRunner();
        response = groovyScriptRunner.run(rpc.getScript(), response);

        if (response == null) {
            return null;
        }

        if (CollectionUtils.isEmpty(rpc.getOutput())) {
            return response;
        }

        Map<String, Object> context = new HashMap<>(1);
        context.put("response", response);

        return walkParams(rpc.getOutput(), context);
    }

    private Map<String, Object> walkParams(List<FlowParam> params, Map<String, Object> context) {
        Map<String, Object> map = new HashMap<>(params.size());

        for (FlowParam param : params) {
            Object value = compileValue(param.getValue(), context);

            if (param.hasChildren()) {
                switch (param.dataType()) {
                    case ARRAY:
                        if (value instanceof List && ((List<Object>) value).size() > 0) {
                            Map<String, Object> childCompileContext = new HashMap<>(context);

                            value = ((List<Object>) value).stream().map(item -> {
                                childCompileContext.put("item", item);
                                return walkParams(param.getChildren(), childCompileContext);
                            }).collect(Collectors.toList());
                        }
                        break;
                    case OBJECT:
                        Map<String, Object> childParams = walkParams(param.getChildren(), context);
                        if (value instanceof Map) {
                            ((Map<String, Object>)value).putAll(childParams);
                        } else {
                            value = childParams;
                        }
                }
            }

            if (value != null) {
                map.put(param.getCode(), value);
            }
        }
        return map;
    }

    private Object compileValue(String mvelExpr, Map<String, Object> context) {
        if (StringUtils.isEmpty(mvelExpr)) {
            return null;
        }

        try {
            return TemplateRuntime.eval(String.format("@{%s}", mvelExpr), context);
        } catch (Exception e) {
            return null;
        }
    }

}
