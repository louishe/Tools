package com.msky.tools.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation type for specifying meta-data about Demo
 * @author aim
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoProperties {
    String value(); // Name
    String category();
    String description();
    String iconFile() default ""; 
    String[] sourceFiles() default "";
}
