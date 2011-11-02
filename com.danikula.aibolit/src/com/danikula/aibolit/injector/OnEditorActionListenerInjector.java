/*
 * Copyright (C) 2011 Alexey Danilov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.danikula.aibolit.injector;

import java.lang.reflect.Method;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.InjectOnEditorActionListener;

/**
 * Injects {@link TextView.OnEditorActionListener#onEditorAction(TextView, int, KeyEvent)} method
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class OnEditorActionListenerInjector extends AbstractMethodInjector<InjectOnEditorActionListener> {

    @Override
    public void doInjection(Object methodOwner, InjectionContext injectionContext, Method sourceMethod, InjectOnEditorActionListener annotation) {
        Class<?>[] argsTypes = new Class<?>[] { TextView.class, int.class, KeyEvent.class };
        Method targetMethod = getMethod(OnEditorActionListener.class, "onEditorAction", argsTypes, sourceMethod);
        OnEditorActionListener onEditorActionListener = createInvokationProxy(OnEditorActionListener.class, methodOwner,
                sourceMethod, targetMethod);

        View view = getViewById(injectionContext.getRootView(), annotation.value());
        checkIsViewAssignable(TextView.class, view.getClass());
        ((TextView) view).setOnEditorActionListener(onEditorActionListener);
    }
}
