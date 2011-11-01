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
