package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.view.View;
import android.widget.AdapterView;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link AdapterView.OnItemSelectedListener#onItemSelected(AdapterView, View, int, long)} method
 * for specified {@link AdapterView}. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectOnItemSelectedListener(R.id.listView)
 * private void onListViewItemSelected(AdapterView&lt;?&gt; parent, View view, int position, long id) {
 *     // process event
 * }
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see AdapterView.OnItemSelectedListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnItemSelectedListener {

    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id. View must be instance of {@link AdapterView}
     */
    int value();

}
