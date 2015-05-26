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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.ServicesResolver;
import com.danikula.aibolit.Validate;
import com.danikula.aibolit.annotation.InjectArrayAdapter;
import com.danikula.aibolit.annotation.InjectOnCheckedChangeListener;
import com.danikula.aibolit.annotation.InjectOnClickListener;
import com.danikula.aibolit.annotation.InjectOnCreateContextMenuListener;
import com.danikula.aibolit.annotation.InjectOnEditorActionListener;
import com.danikula.aibolit.annotation.InjectOnFocusChangeListener;
import com.danikula.aibolit.annotation.InjectOnItemClickListener;
import com.danikula.aibolit.annotation.InjectOnItemSelectedListener;
import com.danikula.aibolit.annotation.InjectOnKeyListener;
import com.danikula.aibolit.annotation.InjectOnLongClickListener;
import com.danikula.aibolit.annotation.InjectOnRadioGroupCheckedChangeListener;
import com.danikula.aibolit.annotation.InjectOnTextChangedListener;
import com.danikula.aibolit.annotation.InjectOnTouchListener;
import com.danikula.aibolit.annotation.InjectResource;
import com.danikula.aibolit.annotation.InjectService;
import com.danikula.aibolit.annotation.InjectSystemService;
import com.danikula.aibolit.annotation.InjectView;

public class InjectorRegister {
    private static final Map<Class<? extends Annotation>, AbstractInjector<?>> INJECTORS_REGISTER;

    private static final List<ServicesResolver> SERVICES_RESOLVERS;

    static {
        SERVICES_RESOLVERS = new LinkedList<ServicesResolver>();
        INJECTORS_REGISTER = new HashMap<Class<? extends Annotation>, AbstractInjector<?>>();

        INJECTORS_REGISTER.put(InjectView.class, new ViewInjector());
        INJECTORS_REGISTER.put(InjectResource.class, new ResourceInjector());
        INJECTORS_REGISTER.put(InjectArrayAdapter.class, new ArrayAdapterInjector());
        INJECTORS_REGISTER.put(InjectSystemService.class, new SystemServiceInjector());
        INJECTORS_REGISTER.put(InjectService.class, new ServiceInjector(SERVICES_RESOLVERS));

        INJECTORS_REGISTER.put(InjectOnClickListener.class, new OnClickListenerInjector());
        INJECTORS_REGISTER.put(InjectOnLongClickListener.class, new OnLongClickListenerInjector());
        INJECTORS_REGISTER.put(InjectOnItemClickListener.class, new OnItemClickListenerInjector());
        INJECTORS_REGISTER.put(InjectOnItemSelectedListener.class, new OnItemSelectedListenerInjector());
        INJECTORS_REGISTER.put(InjectOnTouchListener.class, new OnTouchListenerInjector());
        INJECTORS_REGISTER.put(InjectOnKeyListener.class, new OnKeyListenerInjector());
        INJECTORS_REGISTER.put(InjectOnFocusChangeListener.class, new OnFocusChangeListenerInjector());
        INJECTORS_REGISTER.put(InjectOnCreateContextMenuListener.class, new OnCreateContextMenuListenerInjector());
        INJECTORS_REGISTER.put(InjectOnTextChangedListener.class, new OnTextChangedListenerInjector());
        INJECTORS_REGISTER.put(InjectOnCheckedChangeListener.class, new OnCheckedChangeInjector());
        INJECTORS_REGISTER.put(InjectOnRadioGroupCheckedChangeListener.class, new OnRadioGroupCheckedChangeInjector());
        INJECTORS_REGISTER.put(InjectOnEditorActionListener.class, new OnEditorActionListenerInjector());
    }

    public static boolean contains(Class<? extends Annotation> annotationClass) {
        return INJECTORS_REGISTER.containsKey(annotationClass);
    }

    public static AbstractFieldInjector<Annotation> getFieldInjector(Class<? extends Annotation> annotationClass) {
        AbstractInjector<?> abstractInjector = INJECTORS_REGISTER.get(annotationClass);
        if (!(abstractInjector instanceof AbstractFieldInjector)){
            throw new InjectingException("There is no registered AbstractFieldInjector for annotation class "  + annotationClass.getName());
        }
        return (AbstractFieldInjector<Annotation>) abstractInjector;
    }
    
    public static AbstractMethodInjector<Annotation> getMethodInjector(Class<? extends Annotation> annotationClass) {
        AbstractInjector<?> abstractInjector = INJECTORS_REGISTER.get(annotationClass);
        if (!(abstractInjector instanceof AbstractMethodInjector)){
            throw new InjectingException("There is no registered AbstractMethodInjector for annotation class "  + annotationClass.getName());
        }
        return (AbstractMethodInjector<Annotation>) abstractInjector;
    }
    
    public static void addServicesResolver(ServicesResolver serviceResolver){
        Validate.notNull(serviceResolver, "InjectionResolver must be not null");
        SERVICES_RESOLVERS.add(serviceResolver);
    }
}
