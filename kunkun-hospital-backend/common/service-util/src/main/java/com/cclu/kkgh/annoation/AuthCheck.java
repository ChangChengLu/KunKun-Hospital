package com.cclu.kkgh.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ChangCheng Lu
 * @date 2023/8/1 16:47
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     *
     * @return 必须包含某个角色
     */
    String mustRole() default "";

}
