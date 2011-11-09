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

import android.view.View.OnKeyListener;

import com.danikula.aibolit.Aibolit;

/**
 * Anotation is used for injecting extra's value into field. See docs for {@link Aibolit} for more information. Can be used only in Activity.
 * 
 * <p>
 * Usage:
 * 
 * <pre>
 * &#064;Extra(name="com.example.application.PhoneNumber") // required extra
 * private String phoneNumber;
 *
 * &#064;Extra(name="com.example.application.FirstName", required=false) // not required extra with default value
 * private String firstName = "undefined";
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @see OnKeyListener
 * 
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Extra {

    /**
     * Returns extra's name
     * 
     * @return String extra's name
     */
    String name();
    
    /**
     * Returns is extra's must be presented in intent
     * 
     * @return boolean <code>true</code> if extra's must be presented in intent
     */
    boolean required() default true;

}
