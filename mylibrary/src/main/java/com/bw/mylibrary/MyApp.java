package com.bw.mylibrary;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApp extends Application {
    //全局的上下文
    public Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        //注册Fresco
        Fresco.initialize(this);
    }
}
