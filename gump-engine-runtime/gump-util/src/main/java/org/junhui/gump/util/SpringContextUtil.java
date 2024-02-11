package org.junhui.gump.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationCla) {
        return applicationContext.getBeansWithAnnotation(annotationCla);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> cla) {
        return applicationContext.getBeansOfType(cla);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }
}
