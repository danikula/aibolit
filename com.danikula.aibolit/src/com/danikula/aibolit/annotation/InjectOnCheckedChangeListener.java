package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.widget.CompoundButton;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link CompoundButton.OnCheckedChangeListener#onCheckedChanged(CompoundButton, boolean)} method
 * for specified {@link CompoundButton}. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectOnCheckedChangeListener(R.id.checkbox)
 * private void onCheckedChanged(android.widget.CompoundButton arg0, boolean arg1) {
 *     // process event
 * }
 * 
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see CompoundButton.OnCheckedChangeListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnCheckedChangeListener {

    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id. View must be instance of {@link CompoundButton}
     */
    int value();

}
