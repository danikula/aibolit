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

import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;

import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.annotation.ViewById;

/**
 * Injects view.
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class ViewByIdInjector extends AbstractFieldInjector<ViewById> {

    @Override
    public void doInjection(Object fieldOwner, InjectionContext injectionContext, Field field, ViewById annotation) {
        String fieldName = field.getName();
        int viewId = annotation.value() != 0 ? annotation.value() : resolveIdByFieldName(injectionContext, fieldName);
        View view = getViewById(injectionContext.getRootView(), viewId);
        if (view == null) {
            String errorPattern = "View with id 0x%s for field named '%s' with type %s is not found";
            throw new InjectingException(String.format(errorPattern, Integer.toHexString(viewId), fieldName,
                    field.getType()));
        }
        checkIsFieldAssignable(field, field.getType(), view.getClass());
        setValue(fieldOwner, field, view);
    }

    private int resolveIdByFieldName(InjectionContext injectionContext, String fieldName) {
        Context context = injectionContext.getAndroidContext();
        Resources resources = context.getResources();
        int viewId = resources.getIdentifier(fieldName, "id", context.getPackageName());
        if (viewId == 0) {
            throw new InjectingException(String.format("View with id R.id.%s is not found", fieldName));
        }
        return viewId;
    }
}