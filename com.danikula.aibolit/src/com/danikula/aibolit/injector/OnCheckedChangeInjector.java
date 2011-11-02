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
