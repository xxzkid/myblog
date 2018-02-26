package org.xxz.myblog.common.annotation;

import java.lang.annotation.*;

/**
 * @author tt
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Security {
    
    String name() default "";
    String type() default "";

}
