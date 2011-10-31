package com.danikula.aibolit.injector;

import java.lang.reflect.Method;

import android.view.View;
import android.view.View.OnLongClickListener;

import com.danikula.aibolit.annotation.InjectOnLongClickListener;

/**
 * Injects {@link View.OnLongClickListener#onLongClick(View)} method
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class OnLongClickListenerInjector extends AbstractMethodInjector<InjectOnLongClickListener> {

    @Override
    public void doInjection(Object methodOwner, View viewHolder, Method sourceMethod, InjectOnLongClickListener annotation) {
        Method targetMethod = getMethod(OnLongClickListener.class, "onLongClick", new Class<?>[] { View.class }, sourceMethod);
        checkMethodSignature(targetMethod, sourceMethod);
        OnLongClickListener listener = createInvokationProxy(OnLongClickListener.class, methodOwner, sourceMethod, targetMethod);

        View view = getViewById(viewHolder, annotation.value());
        view.setOnLongClickListener(listener);
    }

}
