package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;
import android.view.View.OnLongClickListener;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link OnLongClickListener#onLongClick(android.view.View)} method for specified {@link View}.
 * See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectOnSearchLongClickListener(R.id.button)
 * private boolean onButtonLongClickListener(View view) {
 *     // process event
 *     return false;
 * }
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see OnLongClickListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnLongClickListener {

    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id
     */
    int value();

}
