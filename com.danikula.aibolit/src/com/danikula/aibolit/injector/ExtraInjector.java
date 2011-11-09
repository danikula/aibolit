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

import android.app.Activity;
import android.os.Bundle;

import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.Extra;

/**
 * Injects extra.
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class ExtraInjector extends AbstractFieldInjector<Extra> {

    @Override
    public void doInjection(Object fieldOwner, InjectionContext injectionContext, Field field, Extra annotation) {
        if(!(injectionContext.getAndroidContext() instanceof Activity)) {
            throw new InjectingException("@Extra annotatina can be used only in activity");
        }
        Activity activity = (Activity) injectionContext.getAndroidContext();
        Bundle extras = activity.getIntent().getExtras();
        String extraName = annotation.name();
        if (annotation.required() && (extras == null || !extras.containsKey(extraName))) {
            throw new InjectingException(String.format("Intent doesn't contain extra named '%s'", extraName));
        }
        Object extraValue = extras != null ? extras.get(extraName) : null;
//        checkIsFieldAssignable(field, field.getType(), extraValue.getClass());
        setValue(fieldOwner, field, extraValue);
    }
}
