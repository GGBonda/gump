package org.junhui.gump.flow.core.functions;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junhui.gump.flow.core.annotation.GumpFunctionBean;
import org.junhui.gump.util.SpringContextUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GumpFunctionManager {

    private static GumpFunctionManager singleton;

    private final Map<String, Pair<Object, Method>> functionMap = new HashMap<>();

    private GumpFunctionManager() {
        init();
    }

    private void init() {
        Map<String, Object> beanMap = getGumpFunctionBeanMap();
        if (MapUtils.isEmpty(beanMap)) {
            return;
        }

        for (Object bean : beanMap.values()) {
            Method[] methods = bean.getClass().getMethods();
            for (Method method : methods) {
                GumpFunctionBean.Function annotation = method.getAnnotation(GumpFunctionBean.Function.class);
                if (annotation == null) {
                    continue;
                }
                functionMap.put(annotation.code(), new ImmutablePair<>(bean, method));
            }
        }
    }

    public static GumpFunctionManager getInstance() {
        if (singleton == null) {
            synchronized (GumpFunctionManager.class) {
                if (singleton == null) {
                    singleton = new GumpFunctionManager();
                }
            }
        }
        return singleton;
    }

    public Map<String, Object> getGumpFunctionBeanMap() {
        return SpringContextUtil.getBeansWithAnnotation(GumpFunctionBean.class);
    }

    public Object invoke(String funcCode, Object... params) throws InvocationTargetException, IllegalAccessException {
        if (!functionMap.containsKey(funcCode)) {
            throw new IllegalArgumentException(String.format("function '%s' does not exist", funcCode));
        }

        Pair<Object, Method> pair = functionMap.get(funcCode);
        Object bean = pair.getLeft();
        Method method = pair.getRight();

        return method.invoke(bean, params);
    }

}
