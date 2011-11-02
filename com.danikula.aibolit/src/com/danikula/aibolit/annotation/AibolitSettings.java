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
 * Anotation is used for pass injecting settings into injector. See docs for {@link Aibolit} for more information.
 * <p>
 * Usage:
 * 
 * <pre>
 * 
 * &#064;AibolitSettings(injectSuperclasses = true)
 * private static class SimpleDialog extends AbstractSimpleDialog {
 * ...
 * }
 * 
 * </pre>
 * 
 * </p>
 * 
 * @see Aibolit
 * @author Alexey Danilov
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AibolitSettings {

    /**
     * Is superclasses should be processed by injector. Set this flag to <code>true</code> if superclass of your class contains
     * fields or methods that should be injected
     * 
     * @return boolean is superclasses should be processed by injector
     */
    boolean injectSuperclasses() default false;

}
