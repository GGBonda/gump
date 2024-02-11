package org.junhui.gump.flow.core.step;


import org.apache.commons.collections4.MapUtils;
import org.junhui.gump.flow.core.annotation.StepProcessorBean;
import org.junhui.gump.util.SpringContextUtil;

import java.util.HashMap;
import java.util.Map;

public class StepProcessorBeanManager {

    private static StepProcessorBeanManager singleton;

    private final Map<String, StepProcessor<?>> map = new HashMap<>();

    private StepProcessorBeanManager() {
        init();
    }

    private void init() {
        Map<String, Object> beanMap = SpringContextUtil.getBeansWithAnnotation(StepProcessorBean.class);
        if (MapUtils.isEmpty(beanMap)) {
            return;
        }

        for (Object bean : beanMap.values()) {
            StepProcessorBean annotation = bean.getClass().getAnnotation(StepProcessorBean.class);
            map.put(annotation.stepCode(), (StepProcessor) bean);
        }
    }

    public static StepProcessorBeanManager getInstance() {
        if (singleton == null) {
            synchronized (StepProcessorBeanManager.class) {
                if (singleton == null) {
                    singleton = new StepProcessorBeanManager();
                }
            }
        }
        return singleton;
    }

    public StepProcessor getProcessor(String stepType) {
        return map.get(stepType);
    }

}
