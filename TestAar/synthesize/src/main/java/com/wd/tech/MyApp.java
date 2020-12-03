package com.wd.tech;

import android.app.Application;
import android.content.Context;

public class MyApp extends Application {
    static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
