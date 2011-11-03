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
import java.lang.reflect.Field;


import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.InjectionContext;

/**
 * Injects field into object
 * 
 * @author Alexey Danilov
 * 
 * @param <A> type of corresponding annotation
 */
public abstract class AbstractFieldInjector<A extends Annotation> extends AbstractInjector<A> {
    
    /**
     * Injects filed into object
     * @param fieldOwner Objects object that contain field
     * @param injectionContext InjectionContext object to be used for retrieving information about injection context
     * @param field Filed injected to be initialized
     * @param annotation T annotation fir providing data for injection
     */
    public abstract void doInjection(Object fieldOwner, InjectionContext injectionContext, Field field, A annotation);

    protected void checkIsFieldAssignable(Field field, Class<?> fieldClass, Class<?> viewClass) {
        if (!fieldClass.isAssignableFrom(viewClass)) {
            String errorPatterm = "Can't cast object with type %s to field named '%s' with type %s";
            throw new InjectingException(String.format(errorPatterm, viewClass, field.getName(), fieldClass.getName()));
        }
    }

    protected void setValue(Object object, Field field, Object value) {
        try {
            field.setAccessible(true);
            field.set(object, value);
        }
        catch (IllegalArgumentException e) {
            processSettingValueError(field, value, e);
        }
        catch (IllegalAccessException e) {
            processSettingValueError(field, value, e);
        }
    }

    private void processSettingValueError(Field field, Object value, Exception e) throws InjectingException {
        String errorPattern = "Error setting value '%s' with type %s to field named '%s' with type %s";
        String error = String.format(errorPattern, value, value.getClass().getName(), field.getName(), field.getType().getName());
        throw new InjectingException(error, e);
    }

}
