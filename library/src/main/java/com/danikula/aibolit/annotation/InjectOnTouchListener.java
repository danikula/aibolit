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

import android.view.View;
import android.view.View.OnTouchListener;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting {@link OnTouchListener#onTouch(View, android.view.MotionEvent)} method for specified view.
 * See docs for {@link Aibolit} for more information.
 * 
 * <p>
 * Usage:
 * <pre>
 * &#064;InjectOnTouchListener(R.id.button)
 * private boolean onButtonTouch(View v, MotionEvent event) {
 *     // handle touch event
 *     return false;
 * }
 * </pre>
 * </p>
 * 
 * @see Aibolit
 * @see OnTouchListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectOnTouchListener {

    /**
     * Returns identifier of view to be used for setting listener
     * 
     * @return int view id
     */
    int value();

}
