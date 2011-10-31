package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link View.OnTouchListener#onTouch(View, android.view.MotionEvent)} method for specified view.
 * See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * <pre>
 * &#064;InjectOnTouchListener(R.id.button)
 * private boolean onButtonTouch(View v, MotionEvent event) {
 *     // handle touch event
 *     return false;
 * }
 * </pre>
 * </p>
 * 
 * @see Aibolit
 * @see View.OnTouchListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnTouchListener {

    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id
     */
    int value();

}
