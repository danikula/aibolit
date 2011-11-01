package com.danikula.aibolit.injector;

import java.lang.reflect.Method;

import android.view.View;
import android.view.View.OnFocusChangeListener;

import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.InjectOnFocusChangeListener;

/**
 * Injects {@link OnFocusChangeListener#onFocusChange(View, boolean)} method
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class OnFocusChangeListenerInjector extends AbstractMethodInjector<InjectOnFocusChangeListener> {

    @Override
    public void doInjection(Object methodOwner, InjectionContext injectionContext, Method sourceMethod, InjectOnFocusChangeListener annotation) {
        Class<?>[] argsTypes = new Class<?>[] { View.class, boolean.class };
        Method targetMethod = getMethod(OnFocusChangeListener.class, "onFocusChange", argsTypes, sourceMethod);
        OnFocusChangeListener onFocusChangeListener = createInvokationProxy(OnFocusChangeListener.class, methodOwner,
                sourceMethod, targetMethod);

        View view = getViewById(injectionContext.getRootView(), annotation.value());
        view.setOnFocusChangeListener(onFocusChangeListener);
    }
}
