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

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting view into field. See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;ViewById(R.id.messageEditText)
 * private EditText messageEditText;
 * 
 * &#064;ViewById
 * private TextView symbolsCountTextVew; // use field's name as identifier
 * 
 * &#064;ViewById(R.id.historyListView)
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
public @interface ViewById {

    /**
     * Returns identifier of view, not required. If value is not specified annotated field's name will be used as indentifier's name 
     * 
     * @return int view id
     */
    int value() default 0;

}
