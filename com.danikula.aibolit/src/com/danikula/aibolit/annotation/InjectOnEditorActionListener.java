package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link OnEditorActionListener#onEditorAction(TextView, int, android.view.KeyEvent)}
 * method for specified {@link TextView}. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectOnEditorActionListener(R.id.editText)
 * boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
 *     // process event
 *     return false;
 * }
 * 
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see OnEditorActionListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnEditorActionListener {

    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id. View must be instance of {@link TextView}
     */
    int value();

}
