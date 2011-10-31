package com.danikula.aibolit.injector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import android.view.View;

import com.danikula.aibolit.InjectingException;

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
     * @param viewHolder View view to be used for resolving injection
     * @param field Filed injected to be initialized
     * @param annotation T annotation fir providing data for injection
     */
    public abstract void doInjection(Object fieldOwner, View viewHolder, Field field, A annotation);
    
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
