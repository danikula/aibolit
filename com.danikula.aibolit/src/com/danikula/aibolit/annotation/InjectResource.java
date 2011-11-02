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
 * Anotation is used for injecting application application resource into field. See docs for {@link Aibolit} for more information.
 * Annotation is applicable for injecting drawable, string, anim, layout, bool, dimen, integer, array, color resources.
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;InjectResource(R.anim.my_anim)
 * private Animation animation;
 * 
 * &#064;InjectResource(R.color.button_text)
 * private ColorStateList buttonText;
 * 
 * &#064;InjectResource(android.R.drawable.btn_default)
 * private Drawable drawable;
 * 
 * &#064;InjectResource(android.R.layout.simple_expandable_list_item_2)
 * private View view;
 * 
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectResource {
    /**
     * Returns identifier of resource
     * 
     * @return int resource's id
     */
    int value();

}
