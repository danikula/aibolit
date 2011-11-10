package com.danikula.aibolit.test;

import android.app.Application;

public class AibolitTestApplication extends Application {
    
    public static final String HTTP_MANAGER = "http_manager";
    
    private HttpManager httpManager;

    @Override
    public void onCreate() {
        super.onCreate();
        
    }
    
    @Override
    public Object getSystemService(String name) {
        if (HTTP_MANAGER.equals(name)) {
            return getHttpManager();
        } else {
            return super.getSystemService(name);    
        }
    }
    
    private HttpManager getHttpManager() {
        if (httpManager == null) {
            httpManager = new HttpManager();
        }
        return httpManager;
    }
    
    // some application service. should be top level class
    public static class HttpManager {
        
        public Object invokeRequest(Object request) {
            Object response = null; 
            // do work... 
            return response;
        }
    }
    
}
