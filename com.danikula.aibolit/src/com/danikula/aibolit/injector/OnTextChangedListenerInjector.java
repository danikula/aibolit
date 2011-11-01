package com.danikula.aibolit.injector;

import java.lang.reflect.Method;

import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.InjectOnTextChangedListener;

/**
 * Injects {@link TextWatcher#onTextChanged(CharSequence, int, int, int)} method
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class OnTextChangedListenerInjector extends AbstractMethodInjector<InjectOnTextChangedListener> {

    @Override
    public void doInjection(Object methodOwner, InjectionContext injectionContext, Method sourceMethod, InjectOnTextChangedListener annotation) {
        Class<?>[] argsTypes = new Class<?>[] { CharSequence.class, int.class, int.class, int.class };
        Method targetMethod = getMethod(TextWatcher.class, "onTextChanged", argsTypes, sourceMethod);
        TextWatcher textWatcher = createInvokationProxy(TextWatcher.class, methodOwner, sourceMethod, targetMethod);

        View view = getViewById(injectionContext.getRootView(), annotation.value());
        checkIsViewAssignable(TextView.class, view.getClass());
        ((TextView) view).addTextChangedListener(textWatcher);
    }
}
