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

import java.lang.reflect.Field;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.InjectArrayAdapter;

/**
 * Injects {@link ArrayAdapter}
 * 
 * @author Alexey Danilov
 *
 */
/*package private*/class ArrayAdapterInjector extends AbstractFieldInjector<InjectArrayAdapter> {

    @Override
    public void doInjection(Object fieldOwner, InjectionContext injectionContext, Field field, InjectArrayAdapter annotation) {
        Context context = injectionContext.getAndroidContext();
        int layoutId = annotation.layoutId();
        int textArrayResourceId = annotation.textArrayResourceId();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, textArrayResourceId, layoutId);

        if (adapter == null) {
            String errorPattern = "Adapter for text array with id 0x%s and layout with id 0x%s for field named '%s' with type %s is not created";
            throw new InjectingException(String.format(errorPattern, Integer.toHexString(textArrayResourceId),
                    Integer.toHexString(layoutId), field.getName(), field.getType()));
        }

        checkIsFieldAssignable(field, field.getType(), adapter.getClass());
        setValue(fieldOwner, field, adapter);
    }
}
