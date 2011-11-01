package com.danikula.aibolit.injector;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;

import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.InjectSystemService;

/**
 * Injects system service.
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class SystemServiceInjector extends AbstractFieldInjector<InjectSystemService> {

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
    public void doInjection(Object fieldOwner, InjectionContext injectionContext, Field field, InjectSystemService annotation) {
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
