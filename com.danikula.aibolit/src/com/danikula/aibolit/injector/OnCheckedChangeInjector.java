package com.danikula.aibolit.injector;

import java.lang.reflect.Method;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.danikula.aibolit.annotation.InjectOnCheckedChangeListener;

/**
 * Injects {@link CompoundButton.OnCheckedChangeListener#onCheckedChanged(CompoundButton, boolean)} method
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class OnCheckedChangeInjector extends AbstractMethodInjector<InjectOnCheckedChangeListener> {

    @Override
    public void doInjection(Object methodOwner, View viewHolder, Method sourceMethod, InjectOnCheckedChangeListener annotation) {
        Class<?>[] argsTypes = new Class<?>[] { CompoundButton.class, boolean.class };
        Method targetMethod = getMethod(OnCheckedChangeListener.class, "onCheckedChanged", argsTypes, sourceMethod);
        View view = getViewById(viewHolder, annotation.value());
        checkIsViewAssignable(CompoundButton.class, view.getClass());

        OnCheckedChangeListener onCheckedChangeListener = createInvokationProxy(OnCheckedChangeListener.class, methodOwner,
                sourceMethod, targetMethod);
        ((CompoundButton) view).setOnCheckedChangeListener(onCheckedChangeListener);
    }
}
