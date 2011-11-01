package com.danikula.aibolit.injector;

import java.lang.reflect.Method;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.InjectOnCheckedChangeListener;

/**
 * Injects {@link CompoundButton.OnCheckedChangeListener#onCheckedChanged(CompoundButton, boolean)} method
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class OnCheckedChangeInjector extends AbstractMethodInjector<InjectOnCheckedChangeListener> {

    @Override
    public void doInjection(Object methodOwner, InjectionContext injectionContext, Method sourceMethod, InjectOnCheckedChangeListener annotation) {
        Class<?>[] argsTypes = new Class<?>[] { CompoundButton.class, boolean.class };
        Method targetMethod = getMethod(OnCheckedChangeListener.class, "onCheckedChanged", argsTypes, sourceMethod);
        View view = getViewById(injectionContext.getRootView(), annotation.value());
        checkIsViewAssignable(CompoundButton.class, view.getClass());

        OnCheckedChangeListener onCheckedChangeListener = createInvokationProxy(OnCheckedChangeListener.class, methodOwner,
                sourceMethod, targetMethod);
        ((CompoundButton) view).setOnCheckedChangeListener(onCheckedChangeListener);
    }
}
