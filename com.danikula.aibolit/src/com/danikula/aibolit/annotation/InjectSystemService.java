package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.content.Context;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting system service into field. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectSystemService(Context.NOTIFICATION_SERVICE)
 * private NotificationManager notificationManager;
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectSystemService {

    /**
     * Returns system service's name, such as {@link Context#WINDOW_SERVICE}
     * 
     * @return String system's name
     */
    String value();

}
