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
package com.danikula.aibolit.injector;

import java.lang.annotation.Annotation;

import android.view.View;

import com.danikula.aibolit.InjectingException;

/**
 * Injects field or method using data from annotation
 * 
 * @author Alexey Danilov
 * 
 * @param <T> type of corresponding annotation
 */
public abstract class AbstractInjector<T extends Annotation> {

    protected View getViewById(View viewHolder, int viewId) {
        View view = viewHolder.findViewById(viewId);
        if (view == null) {
            String errorPattern = "There is no view with id 0x%s in view %s";
            throw new InjectingException(String.format(errorPattern, Integer.toHexString(viewId), viewHolder));
        }
        return view;
    }

}
