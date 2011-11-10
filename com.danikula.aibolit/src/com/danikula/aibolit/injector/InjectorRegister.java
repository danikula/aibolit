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

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.annotation.Extra;
import com.danikula.aibolit.annotation.OnCheckedChange;
import com.danikula.aibolit.annotation.OnClick;
import com.danikula.aibolit.annotation.OnCreateContextMenu;
import com.danikula.aibolit.annotation.OnEditorAction;
import com.danikula.aibolit.annotation.OnFocusChange;
import com.danikula.aibolit.annotation.OnItemClick;
import com.danikula.aibolit.annotation.OnItemSelected;
import com.danikula.aibolit.annotation.OnKey;
import com.danikula.aibolit.annotation.OnLongClick;
import com.danikula.aibolit.annotation.OnRadioGroupCheckedChange;
import com.danikula.aibolit.annotation.OnTextChanged;
import com.danikula.aibolit.annotation.OnTouch;
import com.danikula.aibolit.annotation.Resource;
import com.danikula.aibolit.annotation.StringArrayAdapter;
import com.danikula.aibolit.annotation.SystemService;
import com.danikula.aibolit.annotation.ViewById;

public class InjectorRegister {
    private static final Map<Class<? extends Annotation>, AbstractInjector<?>> INJECTORS_REGISTER;

    static {
        INJECTORS_REGISTER = new HashMap<Class<? extends Annotation>, AbstractInjector<?>>();

        INJECTORS_REGISTER.put(ViewById.class, new ViewByIdInjector());
        INJECTORS_REGISTER.put(Extra.class, new ExtraInjector());
        INJECTORS_REGISTER.put(Resource.class, new ResourceInjector());
        INJECTORS_REGISTER.put(StringArrayAdapter.class, new ArrayAdapterInjector());
        INJECTORS_REGISTER.put(SystemService.class, new SystemServiceInjector());

        INJECTORS_REGISTER.put(OnClick.class, new OnClickListenerInjector());
        INJECTORS_REGISTER.put(OnLongClick.class, new OnLongClickListenerInjector());
        INJECTORS_REGISTER.put(OnItemClick.class, new OnItemClickListenerInjector());
        INJECTORS_REGISTER.put(OnItemSelected.class, new OnItemSelectedListenerInjector());
        INJECTORS_REGISTER.put(OnTouch.class, new OnTouchListenerInjector());
        INJECTORS_REGISTER.put(OnKey.class, new OnKeyListenerInjector());
        INJECTORS_REGISTER.put(OnFocusChange.class, new OnFocusChangeListenerInjector());
        INJECTORS_REGISTER.put(OnCreateContextMenu.class, new OnCreateContextMenuListenerInjector());
        INJECTORS_REGISTER.put(OnTextChanged.class, new OnTextChangedListenerInjector());
        INJECTORS_REGISTER.put(OnCheckedChange.class, new OnCheckedChangeInjector());
        INJECTORS_REGISTER.put(OnRadioGroupCheckedChange.class, new OnRadioGroupCheckedChangeInjector());
        INJECTORS_REGISTER.put(OnEditorAction.class, new OnEditorActionListenerInjector());
    }

    public static boolean contains(Class<? extends Annotation> annotationClass) {
        return INJECTORS_REGISTER.containsKey(annotationClass);
    }

    public static AbstractInjector<Annotation> getInjector(Class<? extends Annotation> annotationClass) {
        AbstractInjector<?> abstractInjector = INJECTORS_REGISTER.get(annotationClass);
        if (abstractInjector == null){
            throw new InjectingException("There is no registered AbstractFieldInjector for annotation class "  + annotationClass.getName());
        }
        return (AbstractInjector<Annotation>) abstractInjector;
    }
}
