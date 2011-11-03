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
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import android.view.View;

import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.InjectionContext;
import com.danikula.aibolit.MethodInvocationHandler;

/**
 * Injects event handler 
 * 
 * @author Alexey Danilov
 * 
 * @param <A> type of corresponding annotation
 */
public abstract class AbstractMethodInjector<A extends Annotation> extends AbstractInjector<A> {

    /**
     * Injects event handler
     * @param methodOwner Objects object that contain method
     * @param injectionContext InjectionContext object to be used for retrieving information about injection context
     * @param sourceMethod method to be invoked as event handler
     * @param annotation T annotation fir providing data for injection
     */
    public abstract void doInjection(Object methodOwner, InjectionContext injectionContext, Method sourceMethod, A annotation);

    protected void checkIsViewAssignable(Class<? extends View> expectedClass, Class<? extends View> actualClass) {
        if (!expectedClass.isAssignableFrom(actualClass)) {
            String errorPattern = "Injecting is allowable only for view with type %s, but not for %s";
            throw new InjectingException(String.format(errorPattern, expectedClass.getName(), actualClass.getName()));
        }
    }

    protected void checkMethodSignature(Method sourceMethod, Method targetMethod) {
        Class<?>[] sourceMethodArgTypes = sourceMethod.getParameterTypes();
        Class<?>[] targetMethodArgTypes = targetMethod.getParameterTypes();

        if (!Arrays.equals(sourceMethodArgTypes, targetMethodArgTypes)) {
            throw new InjectingException(String.format("Method has incorrect parameters: %s. Expected: %s",
                    Arrays.toString(targetMethodArgTypes), Arrays.toString(sourceMethodArgTypes)));
        }
        if (!sourceMethod.getReturnType().equals(targetMethod.getReturnType())) {
            throw new InjectingException(String.format("Method has incorrect return type: %s. Expected: %s",
                    targetMethod.getReturnType(), sourceMethod.getReturnType()));
        }
    }

    protected Method getMethod(Class<?> methodOwner, String methodName, Class<?>[] argsTypes, Method sourceMethod) {
        String errorPattern = "Error getting method named '%s' from class %s";
        try {
            Method method = methodOwner.getMethod(methodName, argsTypes);
            checkMethodSignature(method, sourceMethod);
            return method;
        }
        catch (SecurityException e) {
            throw new IllegalArgumentException(String.format(errorPattern, methodName, methodOwner.getName()), e);
        }
        catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(String.format(errorPattern, methodName, methodOwner.getName()), e);
        }
    }

    protected <H> H createInvokationProxy(Class<H> proxyClass, Object methodOwner, Method sourceMethod, Method targetMethod) {
        MethodInvocationHandler methodInvocationHandler = new MethodInvocationHandler(methodOwner, sourceMethod, targetMethod);
        ClassLoader classLoader = proxyClass.getClassLoader();
        return (H) Proxy.newProxyInstance(classLoader, new Class[] { proxyClass }, methodInvocationHandler);
    }
}
