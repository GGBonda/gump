package org.junhui.gump.flow.core.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StepMetadata {

    /**
     * 环节中文名
     * */
    String name();

    /**
     * 环节类型
     * */
    String type();

    /**
     * 环节编译成的java代码
     * */
    String[] codes();

    /**
     * 编译环节所需要import的类
     * */
    Class<?>[] imports() default {};

}
