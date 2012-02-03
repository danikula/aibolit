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
