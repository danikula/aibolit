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

package com.danikula.aibolit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.view.InflateException;
import android.view.View;

import com.danikula.aibolit.annotation.AibolitSettings;
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
import com.danikula.aibolit.injector.AbstractFieldInjector;
import com.danikula.aibolit.injector.AbstractMethodInjector;
import com.danikula.aibolit.injector.InjectorRegister;

/**
 * Does injections into object. <br/>
 * Class can inject:
 * <ul>
 * <li>Views annotated by {@link ViewById}</li>
 * <li>Inflated layout annotated by {@link Resource}</li>
 * <li>Application resources (drawable, string, animation, boolean, dimension, integer, array, color) annotated by
 * {@link Resource}.</li>
 * <li>ArrayAdapter annotated by {@link StringArrayAdapter}</li>
 * <li>System services or custom application services annotated by {@link SystemService}</li>
 * <li>Events handlers annotated by:
 * <ul>
 * <li> {@link OnCheckedChange}</li>
 * <li> {@link OnClick}</li>
 * <li> {@link OnCreateContextMenu}</li>
 * <li> {@link OnEditorAction}</li>
 * <li> {@link OnFocusChange}</li>
 * <li> {@link OnItemClick}</li>
 * <li> {@link OnItemSelected}</li>
 * <li> {@link OnKey}</li>
 * <li> {@link OnLongClick}</li>
 * <li> {@link OnRadioGroupCheckedChange}</li>
 * <li> {@link OnTextChanged}</li>
 * <li> {@link OnTouch}</li>
 * </ul>
 * </li>
 * </ul>
 * 
 * Typical usage :
 * 
 * <pre>
 * public class AibolitChatActivity extends Activity {
 * 
 *     // annotate fields to be injected...
 *     
 *     &#064;ViewById(R.id.messageEditText)
 *     private EditText messageEditText;
 * 
 *     &#064;ViewById
 *     private ListView historyListView; // use field's name as identifier 
 * 
 *     &#064;Resource(R.string.symbols_count)
 *     private String symbolsCountPattern;
 *     
 *     &#064;SystemService(Context.NOTIFICATION_SERVICE)
 *     private NotificationManager notificationManager;
 *     
 *     &#064;InjectService
 *     private HttpManager httpManager;
 *     
 *     &#064;Resource(R.layout.content)
 *     private View content;
 *     
 *     &#064;Extra(name="com.example.application.PhoneNumber") // required extra
 *     private String phoneNumber;
 * 
 *     &#064;Extra(name="com.example.application.FirstName", required=false) // not required extra with default value
 *     private String firstName = "undefined";  
 *     
 *     ...
 * 
 *     &#064;Override
 *     public void onCreate(Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 * 
 *         setContentView(R.layout.chat_activity);
 *         // initialize annotated fields and methods
 *         Aibolit.doInjections(this);
 *         
 *         // or just Aibolit.setInjectedContentView(this);
 *         
 *         ...
 *     }
 * 
 *     // annotate event handlers... 
 *     
 *     &#064;OnClick(R.id.sendButton)
 *     private void onSendButtonClick(View v) {
 *         // handle onClick event
 *     }
 * 
 *     &#064;OnClick(R.id.clearHistoryButton)
 *     private void onClearHistoryButtonClick(View v) {
 *         // handle onClick event
 *     }
 * 
 *     &#064;OnTextChanged(R.id.messageEditText)
 *     public void onMessageTextChanged(CharSequence s, int start, int before, int count) {
 *         // handle text changed event
 *     }
 *     
 *     ...
 * 
 * }
 * </pre>
 * <p>
 * To inject custom application service just override {@link Application#getSystemService(String)}:
 * 
 * <pre>
 * public class AibolitTestApplication extends Application {
 * 
 *     public static final String HTTP_MANAGER = &quot;http_manager&quot;;
 * 
 *     private HttpService httpService = new HttpService(); // custom application service
 * 
 *     &#064;Override
 *     public Object getSystemService(String name) {
 *         if (HTTP_MANAGER.equals(name)) {
 *             return getHttpManager();
 *         }
 *         else {
 *             return super.getSystemService(name);
 *         }
 *     }
 * }
 * 
 * // then you can use HttpService anywhere you want:
 * 
 * public class AibolitChatActivity extends Activity {
 * 
 *     &#064;SystemService(AibolitTestApplication.HTTP_MANAGER)
 *     private HttpManager httpManager;
 * 
 *     &#064;Override
 *     public void onCreate(Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 * 
 *         setContentView(R.layout.chat_activity);
 *         Aibolit.doInjections(this);
 * 
 *         httpService.doRequest(); // use injected service
 *     }
 * }
 * 
 * </pre>
 * 
 * Note: If superclass also should be injected just annotate your class with {@link AibolitSettings} annotation with parameter
 * {@link AibolitSettings#injectSuperclasses()} equal to <code>true</code>
 * 
 * @author Alexey Danilov
 * 
 */
public class Aibolit {

    /**
     * Injects all fields and methods marked by injection annotations in object.
     * <p>
     * See full list of injection annotations in javadoc for this class.
     * </p>
     * 
     * @param activity Activity an activity to be processed and which content will be used for resolving injections, can't be
     *            <code>null</code>
     * @throws IllegalArgumentException <code>activity</code> is <code>null</code>
     * @throws InflateException if any injection error has occured
     */
    public static void doInjections(Activity activity) {
        Validate.notNull(activity, "Can't process null activity");
        doInjections(activity, new VisibleInjectionContext(activity));
    }

    /**
     * Injects all fields and methods marked by injection annotations in object.
     * <p>
     * See full list of injection annotations in javadoc for this class.
     * </p>
     * 
     * @param dialog Dialog dialog to be processed and which content will be used for resolving injections, can't be
     *            <code>null</code>
     * @throws IllegalArgumentException if <code>dialog</code> is <code>null</code>
     * @throws InflateException if any injection error has occured
     */
    public static void doInjections(Dialog dialog) {
        Validate.notNull(dialog, "Can't process null dialog");
        doInjections(dialog, new VisibleInjectionContext(dialog));
    }

    /**
     * Injects all fields and methods marked by injection annotations in object.
     * <p>
     * See full list of injection annotations in javadoc for this class.
     * </p>
     * 
     * @param view View view to be processed and that will be used for resolving injections, can't be <code>null</code>
     * @throws IllegalArgumentException if <code>view</code> is <code>null</code>
     * @throws InflateException if any injection error has occured
     */
    public static void doInjections(View view) {
        Validate.notNull(view, "Can't process null view");
        doInjections(view, view);
    }

    /**
     * Inject all fields and methods marked by injection annotations in object.
     * <p>
     * See full list of injection annotations in javadoc for this class.
     * </p>
     * 
     * @param patient Object an object to be processed, can't be <code>null</code>
     * @param view View a view to be used for resolving injections, can't be <code>null</code>
     * @throws IllegalArgumentException if any argument is <code>null</code>
     * @throws InflateException if any injection error has occured
     */
    public static void doInjections(Object patient, View view) {
        Validate.notNull(patient, "Can't inject in null object");
        Validate.notNull(view, "Can't process null view");

        doInjections(patient, new VisibleInjectionContext(view));
    }

    /**
     * Injects all fields and methods marked by injection annotations in object.
     * <p>
     * See full list of injection annotations in javadoc for this class.
     * </p>
     * 
     * @param patient Object an object to be processed, can't be <code>null</code>
     * @param activity Activity activity which content will be used for resolving injections, can't be <code>null</code>
     * @throws IllegalArgumentException if any argument is <code>null</code>
     * @throws InflateException if any injection error has occured
     */
    public static void doInjections(Object patient, Activity activity) {
        doInjections(patient, new VisibleInjectionContext(activity));
    }

    /**
     * Injects all fields and methods marked by injection annotations in object.
     * <p>
     * See full list of injection annotations in javadoc for this class.
     * </p>
     * 
     * Note: this method is applicable only for injection fields in object without any visible presentation (Service,
     * BroadcastReceiver, etc...), so you can use it only for injecting fields annotated with {@link InjectService},
     * {@link SystemService}, {@link Resource}, InjectArrayAdapter
     * 
     * @param patient Object an object to be processed, can't be <code>null</code>
     * @param context Context android context, can't be <code>null</code>
     * @throws IllegalArgumentException if any argument is <code>null</code>
     * @throws InflateException if any injection error has occured
     */
    public static void doInjections(Object patient, Context context) {
        doInjections(patient, new InvisibleInjectionContext(context));
    }

    /**
     * Sets content for specified activity and injects all fields and methods marked by injection annotations in object.
     * <p>
     * See full list of injection annotations in javadoc for this class.
     * </p>
     * 
     * @param activity Activity an activity to be processed and which content will be used for resolving injections, can't be
     *            <code>null</code>
     * @param layoutId int layout id to be inflated
     * @throws IllegalArgumentException if <code>activity</code> is <code>null</code>
     * @throws InflateException if any injection error has occured
     */
    public static void setInjectedContentView(Activity activity, int layoutId) {
        activity.setContentView(layoutId);
        doInjections(activity);
    }

    /**
     * Sets content for specified activity and injects all fields and methods marked by injection annotations in object.
     * <p>
     * See full list of injection annotations in javadoc for this class.
     * </p>
     * 
     * @param activity Activity an activity to be processed and which content will be used for resolving injections, can't be
     *            <code>null</code>
     * @param contentView View view to be inflated
     * @throws IllegalArgumentException if any argument is <code>null</code>
     * @throws InflateException if any injection error has occured
     */
    public static void setInjectedContentView(Activity activity, View contentView) {
        activity.setContentView(contentView);
        doInjections(activity);
    }

    /**
     * Sets content for specified dialog and injects all fields and methods marked by injection annotations in object.
     * <p>
     * See full list of injection annotations in javadoc for this class.
     * </p>
     * 
     * @param dialog Dialog a dialog to be processed and which content will be used for resolving injections, can't be
     *            <code>null</code>
     * @param layoutId int layout id to be inflated
     * @throws IllegalArgumentException if <code>dialog</code> is <code>null</code>
     * @throws InflateException if any injection error has occured
     */
    public static void setInjectedContentView(Dialog dialog, int layoutId) {
        dialog.setContentView(layoutId);
        doInjections(dialog);
    }

    /**
     * Sets content for specified dialog and injects all fields and methods marked by injection annotations in object.
     * <p>
     * See full list of injection annotations in javadoc for this class.
     * </p>
     * 
     * @param dialog Dialog a dialog to be processed and which content will be used for resolving injections, can't be
     *            <code>null</code>
     * @param contentView View view to be inflated
     * @throws IllegalArgumentException if <code>dialog</code> is <code>null</code>
     * @throws InflateException if any injection error has occured
     */
    public static void setInjectedContentView(Dialog dialog, View contentView) {
        dialog.setContentView(contentView);
        doInjections(dialog);
    }

    public static void doInjections(Object patient, InjectionContext injectionContext) {
        Validate.notNull(patient, "Can't inject in null object");
        Validate.notNull(injectionContext, "Can't process null injection context");

        Class<?> holderClass = patient.getClass();
        AibolitSettings aibolitSettings = patient.getClass().getAnnotation(AibolitSettings.class);
        boolean injectSuperclasses = aibolitSettings != null && aibolitSettings.injectSuperclasses();

        List<Field> fields = getFieldsList(holderClass, injectSuperclasses);
        injectFields(patient, injectionContext, fields);

        List<Method> methods = getMethodsList(holderClass, injectSuperclasses);
        injectMethods(patient, injectionContext, methods);
    }

    private static ArrayList<Field> getFieldsList(Class<?> classToInspect, boolean includeSuperclassFields) {
        ArrayList<Field> fieldsList = new ArrayList<Field>(Arrays.asList(classToInspect.getDeclaredFields()));
        if (includeSuperclassFields && classToInspect.getSuperclass() != null) {
            fieldsList.addAll(getFieldsList(classToInspect.getSuperclass(), includeSuperclassFields));
        }
        return fieldsList;
    }

    private static ArrayList<Method> getMethodsList(Class<?> classToInspect, boolean includeSuperclassFields) {
        ArrayList<Method> methodsList = new ArrayList<Method>(Arrays.asList(classToInspect.getDeclaredMethods()));
        if (includeSuperclassFields && classToInspect.getSuperclass() != null) {
            methodsList.addAll(getMethodsList(classToInspect.getSuperclass(), includeSuperclassFields));
        }
        return methodsList;
    }

    private static void injectFields(Object holder, InjectionContext injectionContext, List<Field> fields) {
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationClass = annotation.annotationType();
                if (InjectorRegister.contains(annotationClass)) {
                    AbstractFieldInjector<Annotation> injector = InjectorRegister.getFieldInjector(annotationClass);
                    injector.doInjection(holder, injectionContext, field, annotation);
                }
            }
        }
    }

    private static void injectMethods(Object holder, InjectionContext injectionContext, List<Method> methods) {
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationClass = annotation.annotationType();
                if (InjectorRegister.contains(annotationClass)) {
                    AbstractMethodInjector<Annotation> injector = InjectorRegister.getMethodInjector(annotationClass);
                    injector.doInjection(holder, injectionContext, method, annotation);
                }
            }
        }
    }

}