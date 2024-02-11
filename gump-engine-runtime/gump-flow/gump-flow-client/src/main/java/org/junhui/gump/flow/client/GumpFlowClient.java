package org.junhui.gump.flow.client;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junhui.gump.flow.compile.JDTCompiler;
import org.junhui.gump.flow.core.compile.java.JavaClassCompiler;
import org.junhui.gump.flow.core.compile.java.JavaCodeCompiler;
import org.junhui.gump.flow.core.entity.GumpFlow;
import org.junhui.gump.flow.core.runner.BaseFlowRunner;
import org.junhui.gump.flow.core.runner.FlowContext;
import org.junhui.gump.flow.core.runner.Input;
import org.junhui.gump.flow.poet.compiler.DefaultJavaCodeCompiler;
import org.junhui.gump.util.GsonManager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GumpFlowClient {

    private static final Map<String, Pair<String, Class<?>>> GUMP_FLOW_CLASS_LOCAL_CACHE = new HashMap<>();

    private final JavaCodeCompiler javaCodeCompiler = new DefaultJavaCodeCompiler();

    private final JavaClassCompiler javaClassCompiler = new JDTCompiler();

    public GumpFlowResponse run(GumpFlow flow, GumpFlowRequest request) {
        Class<?> cla = compile(flow);

        FlowContext flowContext = new FlowContext(
                Input.builder().input(request.getInput()).build()
        );
        try {
            BaseFlowRunner flowRunner = (BaseFlowRunner) cla.getConstructor(FlowContext.class, GumpFlow.class).newInstance(flowContext, flow);
            flowRunner.run();

            return GumpFlowResponse.success(flowContext.getOutput());
        } catch (Exception e) {
            return GumpFlowResponse.fail(e.getMessage(), flowContext.getCurrentStepCode());
        }
    }

    public Class<?> compile(GumpFlow flow) {
        String appCode = flow.getAppCode();
        String code = flow.getCode();
        String version = DigestUtils.md5Hex(GsonManager.getInstance().toJSONString(flow));

        String cacheKey = String.format("%s.%s", appCode, code);
        if (GUMP_FLOW_CLASS_LOCAL_CACHE.containsKey(cacheKey)) {
            Pair<String, Class<?>> pair = GUMP_FLOW_CLASS_LOCAL_CACHE.get(cacheKey);

            if (Objects.equals(pair.getLeft(), version)) {
                return pair.getValue();
            }
        }

        String javaCode = javaCodeCompiler.compile(flow);

        Map<String, String> javaCodes = new HashMap<>(1);
        javaCodes.put(cacheKey, javaCode);
        Map<String, Class<?>> classMap = javaClassCompiler.compile(javaCodes);

        Class<?> res = classMap.get(cacheKey);
        GUMP_FLOW_CLASS_LOCAL_CACHE.put(cacheKey, new ImmutablePair<>(version, res));
        return res;
    }
}
