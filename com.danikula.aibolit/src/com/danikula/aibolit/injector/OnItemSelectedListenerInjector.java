package com.danikula.aibolit.injector;

import java.lang.reflect.Method;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.InjectOnItemSelectedListener;

/**
 * Injects {@link AdapterView.OnItemSelectedListener#onItemSelected(AdapterView, View, int, long) } method
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class OnItemSelectedListenerInjector extends AbstractMethodInjector<InjectOnItemSelectedListener> {

    @Override
    public void doInjection(Object methodOwner, InjectionContext injectionContext, Method sourceMethod, InjectOnItemSelectedListener annotation) {
        Class<?>[] argsTypes = new Class<?>[] { AdapterView.class, View.class, int.class, long.class };
        Method targetMethod = getMethod(OnItemSelectedListener.class, "onItemSelected", argsTypes, sourceMethod);
        OnItemSelectedListener onItemSelectedListener = createInvokationProxy(OnItemSelectedListener.class, methodOwner,
                sourceMethod, targetMethod);

        View view = getViewById(injectionContext.getRootView(), annotation.value());
        checkIsViewAssignable(AdapterView.class, view.getClass());
        ((AdapterView<?>) view).setOnItemSelectedListener(onItemSelectedListener);
    }
}
