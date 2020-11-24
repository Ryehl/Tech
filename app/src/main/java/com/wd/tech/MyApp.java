package com.wd.tech;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.arcsoft.face.ErrorInfo;
import com.arcsoft.face.FaceEngine;
import com.bumptech.glide.request.target.ViewTarget;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mmkv.MMKV;

import cn.jpush.im.android.api.JMessageClient;
import io.realm.Realm;

public class MyApp extends Application {
    private final String TAG = "MyApp";
    public static Context context;
    private final String APP_ID = "6EuS9gPy1PntD3DsryuhpR9srnZZcnCFWYpabiJCC6cy";
    private final String SDK_KEY = "AhEz1d9XhcSyUqe7SHjpJqsaEPzRjUcn3BEkSGCyyLhN";

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
        //set flag
        JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_SOUND);

        //Realm
        Realm.init(this);

        //人脸识别
        int code = FaceEngine.activeOnline(this, APP_ID, SDK_KEY);
        if (code == ErrorInfo.MOK) {
            Log.e(TAG, "onCreate: success");
        } else {
            Log.e(TAG, "onCreate: " + code);
        }
    }
}
