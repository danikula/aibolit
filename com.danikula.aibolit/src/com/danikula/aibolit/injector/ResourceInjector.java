package com.danikula.aibolit.injector;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.danikula.aibolit.InjectingException;
import com.danikula.aibolit.annotation.InjectResource;

/**
 * Injects application resource(drawable, string, anim, layout, bool, dimen, integer, array, color)
 * 
 * @author Alexey Danilov
 * 
 */
/* package private */class ResourceInjector extends AbstractFieldInjector<InjectResource> {

    @Override
    public void doInjection(Object fieldOwner, View viewHolder, Field field, InjectResource annotation) {
        Context context = viewHolder.getContext();
        Resources resources = context.getResources();
        Class<?> fieldType = field.getType();
        int resourceId = annotation.value();
        String resourseTypeName = resources.getResourceTypeName(resourceId);
        Object resourse = null;
        if ("drawable".equals(resourseTypeName)) {
            resourse = resources.getDrawable(resourceId);
        }
        else if ("string".equals(resourseTypeName)) {
            resourse = resources.getString(resourceId);
        }
        else if ("anim".equals(resourseTypeName)) {
            resourse = AnimationUtils.loadAnimation(context, resourceId);
        }
        else if ("layout".equals(resourseTypeName)) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            resourse = layoutInflater.inflate(resourceId, null);
        }
        else if ("bool".equals(resourseTypeName)) {
            resourse = resources.getBoolean(resourceId);
        }
        else if ("dimen".equals(resourseTypeName)) {
            resourse = resources.getDimension(resourceId);
        }
        else if ("integer".equals(resourseTypeName)) {
            resourse = resources.getInteger(resourceId);
        }
        else if ("array".equals(resourseTypeName)) {
            if (String[].class.isAssignableFrom(fieldType)) {
                resourse = resources.getStringArray(resourceId);
            }
            if (int[].class.isAssignableFrom(fieldType)) {
                resourse = resources.getIntArray(resourceId);
            }
            else if (TypedArray.class.isAssignableFrom(fieldType)) {
                resourse = resources.obtainTypedArray(resourceId);
            }
        }
        else if ("color".equals(resourseTypeName)) {
            if (ColorStateList.class.isAssignableFrom(fieldType)) {
                resourse = resources.getColorStateList(resourceId);
            }
            else if (int.class.isAssignableFrom(fieldType)) {
                resourse = resources.getColor(resourceId);
            }
        }
        else {
            throw new InflateException(String.format("Unsupported type '%s'", resourseTypeName));
        }
        if (resourse == null) {
            String errorPattern = "Resource with id 0x%s for field named '%s' with type %s not found";
            throw new InjectingException(String.format(errorPattern, Integer.toHexString(resourceId), field.getName(), fieldType));
        }
        setValue(fieldOwner, field, resourse);
    }
}
