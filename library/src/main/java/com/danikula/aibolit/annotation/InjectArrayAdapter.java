/*
 * Copyright (C) 2011 Alexey Danilov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.danikula.aibolit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link ArrayAdapter} using application resources. See docs for {@link Aibolit} for more
 * information.
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectArrayAdapter(textArrayResourceId = R.array.numbers, layoutId = android.R.layout.simple_list_item_1)
 * private ArrayAdapter&lt;CharSequence&gt; adapter;
 * 
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see ArrayAdapter
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectArrayAdapter {

    /**
     * Returns identifier of string array from resources
     * 
     * @return int string array id
     */
    int textArrayResourceId();

    /**
     * Returns identifier of layout to be inflated for {@link AdapterView} row
     * 
     * @return int layout id
     */
    int layoutId();

}
