package com.danikula.aibolit;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;

public class VisibleInjectionContext implements InjectionContext {
    
    private View rootView;
    
    public VisibleInjectionContext(View rootView) {
        this.rootView = rootView;
    }

    public VisibleInjectionContext(Activity activity) {
        this.rootView = activity.getWindow().getDecorView();
    }

    public VisibleInjectionContext(Dialog dialog) {
        this.rootView = dialog.getWindow().getDecorView();
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public Context getAndroidContext() {
        return rootView.getContext();
    }
}
