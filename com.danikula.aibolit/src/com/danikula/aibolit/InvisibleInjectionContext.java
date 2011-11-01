package com.danikula.aibolit;

import android.content.Context;
import android.view.View;

public class InvisibleInjectionContext implements InjectionContext {

    private Context context;

    public InvisibleInjectionContext(Context context) {
        this.context = context;
    }

    @Override
    public View getRootView() {
        throw new UnsupportedOperationException(InvisibleInjectionContext.class.getSimpleName() + " do not have visible presentation!");
    }

    @Override
    public Context getAndroidContext() {
        return context;
    }

}
