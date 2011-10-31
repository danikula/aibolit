package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link View.OnClickListener#onClick(View)} method for specified {@link View}. See docs for
 * {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectOnClickListener(R.id.button)
 * private void onButtonClickListener(View view) {
 *     // process event
 * }
 * 
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see View.OnClickListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnClickListener {

    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id
     */
    int value();

}
