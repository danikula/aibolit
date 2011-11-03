package com.danikula.aibolit.test;

import android.test.ActivityInstrumentationTestCase2;

public class TestInjection extends ActivityInstrumentationTestCase2<TestInjectActivity> {

    private TestInjectActivity activity;

    public TestInjection() {
        super("com.danikula.aibolit.test", TestInjectActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
    }

    public void testViewInjection() {
        assertNotNull(activity.textView);
    }

}
