package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;
import android.view.View.OnCreateContextMenuListener;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting
 * {@link OnCreateContextMenuListener#onCreateContextMenu(android.view.ContextMenu, View, android.view.ContextMenu.ContextMenuInfo)}
 * method for specified {@link View}. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectOnCreateContextMenuListener(R.id.editText)
 * void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
 *  process event
 * }
 * 
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see OnCreateContextMenuListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnCreateContextMenuListener {
    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id
     */
    int value();

}
