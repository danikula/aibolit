package com.danikula.aibolit.injector;

import java.lang.reflect.Method;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.danikula.aibolit.annotation.InjectOnEditorActionListener;

/**
 * Injects {@link TextView.OnEditorActionListener#onEditorAction(TextView, int, KeyEvent)} method
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class OnEditorActionListenerInjector extends AbstractMethodInjector<InjectOnEditorActionListener> {

    @Override
    public void doInjection(Object methodOwner, View viewHolder, Method sourceMethod, InjectOnEditorActionListener annotation) {
        Class<?>[] argsTypes = new Class<?>[] { TextView.class, int.class, KeyEvent.class };
        Method targetMethod = getMethod(OnEditorActionListener.class, "onEditorAction", argsTypes, sourceMethod);
        OnEditorActionListener onEditorActionListener = createInvokationProxy(OnEditorActionListener.class, methodOwner,
                sourceMethod, targetMethod);

        View view = getViewById(viewHolder, annotation.value());
        checkIsViewAssignable(TextView.class, view.getClass());
        ((TextView) view).setOnEditorActionListener(onEditorActionListener);
    }
}
