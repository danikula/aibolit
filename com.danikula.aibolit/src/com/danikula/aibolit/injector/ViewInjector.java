package com.danikula.aibolit.injector;

import java.lang.reflect.Field;

import android.view.View;

import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.annotation.InjectView;

/**
 * Injects view.
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class ViewInjector extends AbstractFieldInjector<InjectView> {

    @Override
    public void doInjection(Object fieldOwner, View viewHolder, Field field, InjectView annotation) {
        int viewId = annotation.value();
        View view = getViewById(viewHolder, viewId);
        if (view == null) {
            String errorPattern = "View with id 0x%s for field named '%s' with type %s not found";
            throw new InjectingException(String.format(errorPattern, Integer.toHexString(viewId), field.getName(),
                    field.getType()));
        }
        checkIsFieldAssignable(field, field.getType(), view.getClass());
        setValue(fieldOwner, field, view);
    }
}
