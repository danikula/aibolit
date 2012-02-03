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

import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.SystemService;

/**
 * Injects system service.
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class SystemServiceInjector extends AbstractFieldInjector<SystemService> {

    @Override
    public void doInjection(Object fieldOwner, InjectionContext injectionContext, Field field, SystemService annotation) {
        Context context = injectionContext.getAndroidContext().getApplicationContext();
        String serviceName = annotation.value();
        Object service = context.getSystemService(serviceName);
        if (service == null) {
            throw new InjectingException(String.format("There is no service named '%s'", serviceName));
        }
        setValue(fieldOwner, field, service);
    }
}
