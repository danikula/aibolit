package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.text.TextWatcher;
import android.widget.TextView;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link TextWatcher#onTextChanged(CharSequence, int, int, int)} method for specified
 * {@link TextView}. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectOnTextChangedListener(R.id.editText)
 * private void onSearchTextChanged(CharSequence s, int start, int before, int count) {
 *     // process event
 * }
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see TextWatcher
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnTextChangedListener {

    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id. View must be instance of {@link TextView} 
     */
    int value();

}
