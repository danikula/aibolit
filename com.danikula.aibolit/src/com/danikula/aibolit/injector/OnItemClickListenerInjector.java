package com.danikula.aibolit.injector;

import java.lang.reflect.Method;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.danikula.aibolit.annotation.InjectOnItemClickListener;

/**
 * Injects {@link AdapterView.OnItemClickListener#onItemClick(AdapterView, View, int, long) } method
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class OnItemClickListenerInjector extends AbstractMethodInjector<InjectOnItemClickListener> {

    @Override
    public void doInjection(Object methodOwner, View viewHolder, Method sourceMethod, InjectOnItemClickListener annotation) {
        Class<?>[] argsTypes = new Class<?>[] { AdapterView.class, View.class, int.class, long.class };
        Method targetMethod = getMethod(OnItemClickListener.class, "onItemClick", argsTypes, sourceMethod);
        OnItemClickListener onItemClickListener = createInvokationProxy(OnItemClickListener.class, methodOwner, sourceMethod,
                targetMethod);

        View view = getViewById(viewHolder, annotation.value());
        checkIsViewAssignable(AdapterView.class, view.getClass());
        ((AdapterView<?>) view).setOnItemClickListener(onItemClickListener);
    }
}
