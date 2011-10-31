package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link OnCheckedChangeListener#onCheckedChanged(RadioGroup, int)} method for specified
 * {@link RadioGroup}. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectOnRadioGroupCheckedChangeListener(R.id.radiogroup)
 * private void onRadioGroupCheckedChanged(RadioGroup rg, int arg1) {
 *     // process event
 * }
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see OnCheckedChangeListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnRadioGroupCheckedChangeListener {

    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id. View must be instance of {@link RadioGroup}
     */
    int value();

}
