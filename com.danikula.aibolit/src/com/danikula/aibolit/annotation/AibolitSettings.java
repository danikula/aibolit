package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for pass injecting settings into injector. See docs for {@link Aibolit} for more information.
 * <p>
 * Usage:
 * 
 * <pre>
 * 
 * &#064;AibolitSettings(injectSuperclasses = true)
 * private static class SimpleDialog extends AbstractSimpleDialog {
 * ...
 * }
 * 
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AibolitSettings {

    /**
     * Is superclasses should be processed by injector. Set this flag to <code>true</code> if superclass of your class contains
     * fields or methods that should be injected
     * 
     * @return boolean is superclasses should be processed by injector
     */
    boolean injectSuperclasses() default false;

}
