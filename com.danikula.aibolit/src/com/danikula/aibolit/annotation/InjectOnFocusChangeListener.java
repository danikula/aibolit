package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;
import android.view.View.OnFocusChangeListener;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link OnFocusChangeListener#onFocusChange(View, boolean)} method for specified
 * {@link View}. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectOnFocusChangeListener(R.id.editText)
 * private void onSearchEditTextFocusChange(View v, boolean hasFocus) {
 *     // process event
 * }
 * 
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see OnFocusChangeListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnFocusChangeListener {

    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id
     */
    int value();

}
