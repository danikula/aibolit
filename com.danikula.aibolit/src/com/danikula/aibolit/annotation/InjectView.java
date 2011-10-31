package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting view into field. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectView(R.id.messageEditText)
 * private EditText messageEditText;
 * 
 * &#064;InjectView(R.id.symbolsCountTextVew)
 * private TextView symbolsCountTextVew;
 * 
 * &#064;InjectView(R.id.historyListView)
 * private ListView historyListView;
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {

    /**
     * Returns identifier of view
     * 
     * @return int view id
     */
    int value();

}
