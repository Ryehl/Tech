package com.bw.tech;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.request.target.ViewTarget;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mmkv.MMKV;

import cn.jpush.im.android.api.JMessageClient;

public class MyApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = this;
        MMKV.initialize(this);

        // You can enable debug mode in developing state. You should close debug mode when release.
        JMessageClient.setDebugMode(true);
        JMessageClient.init(this);
        ViewTarget.setTagId(R.id.glide_tag);
    }
}
