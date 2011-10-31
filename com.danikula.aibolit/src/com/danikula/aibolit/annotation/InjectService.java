package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.danikula.aibolit.Aibolit;
import com.danikula.aibolit.ServicesResolver;

/**
 * Anotation is used for injecting application service into field. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectService
 * private HttpService httpService; // inject application service
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see ServicesResolver
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectService {
    // no parameters
}
