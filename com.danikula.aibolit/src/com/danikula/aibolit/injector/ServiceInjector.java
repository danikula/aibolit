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
import java.util.List;


import com.danikula.aibolit.Aibolit;
import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.ServicesResolver;
import com.danikula.aibolit.annotation.InjectService;

/**
 * Injects application service. Client code have to add custom {@link ServicesResolver} with help method
 * {@link Aibolit#addServicesResolver(ServicesResolver)}
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class ServiceInjector extends AbstractFieldInjector<InjectService> {

    private List<ServicesResolver> resolvers;

    public ServiceInjector(List<ServicesResolver> resolvers) {
        this.resolvers = resolvers;
    }

    @Override
    public void doInjection(Object fieldOwner, InjectionContext injectionContext, Field field, InjectService annotation) {
        Object service = null;
        Class<?> serviceClass = field.getType();
        for (ServicesResolver injectionResolver : resolvers) {
            service = injectionResolver.resolve(serviceClass);
            if (service != null) {
                break;
            }
        }
        if (service == null) {
            String errorPattern = "There is no registered service for field named '%s' with type %s";
            throw new InjectingException(String.format(errorPattern, field.getName(), serviceClass.getName()));
        }

        setValue(fieldOwner, field, service);
    }
}
