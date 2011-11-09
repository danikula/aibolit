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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    // @formatter:off
    private static final Set<String> SUPPORTED_SERVICES = new HashSet<String>(Arrays.asList(
            Context.WINDOW_SERVICE,
            Context.LAYOUT_INFLATER_SERVICE,
            Context.ACTIVITY_SERVICE,
            Context.POWER_SERVICE,
            Context.ALARM_SERVICE,
            Context.NOTIFICATION_SERVICE,
            Context.KEYGUARD_SERVICE,
            Context.LOCATION_SERVICE,
            Context.SEARCH_SERVICE,
            Context.VIBRATOR_SERVICE,
            Context.CONNECTIVITY_SERVICE,
            Context.WIFI_SERVICE,
            Context.INPUT_METHOD_SERVICE
    ));
    // @formatter:on

    @Override
    public void doInjection(Object fieldOwner, InjectionContext injectionContext, Field field, SystemService annotation) {
        Context context = injectionContext.getAndroidContext();
        String serviceName = annotation.value();
        if (SUPPORTED_SERVICES.contains(serviceName)) {
            Object service = context.getSystemService(serviceName);
            setValue(fieldOwner, field, service);
        }
        else {
            String errorPattern = "Can't inject service named '%s'. List of supported services: %s";
            throw new InjectingException(String.format(errorPattern, serviceName, SUPPORTED_SERVICES));
        }
    }
}
