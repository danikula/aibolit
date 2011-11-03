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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.InjectOnItemClickListener;

/**
 * Injects {@link AdapterView.OnItemClickListener#onItemClick(AdapterView, View, int, long) } method
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class OnItemClickListenerInjector extends AbstractMethodInjector<InjectOnItemClickListener> {

    @Override
    public void doInjection(Object methodOwner, InjectionContext injectionContext, Method sourceMethod, InjectOnItemClickListener annotation) {
        Class<?>[] argsTypes = new Class<?>[] { AdapterView.class, View.class, int.class, long.class };
        Method targetMethod = getMethod(OnItemClickListener.class, "onItemClick", argsTypes, sourceMethod);
        OnItemClickListener onItemClickListener = createInvokationProxy(OnItemClickListener.class, methodOwner, sourceMethod,
                targetMethod);

        View view = getViewById(injectionContext.getRootView(), annotation.value());
        checkIsViewAssignable(AdapterView.class, view.getClass());
        ((AdapterView<?>) view).setOnItemClickListener(onItemClickListener);
    }
}
