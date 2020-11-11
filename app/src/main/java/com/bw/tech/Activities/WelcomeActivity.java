package com.bw.tech.Activities;

import android.Manifest;
import android.content.Intent;

import androidx.core.app.ActivityCompat;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.R;

public class WelcomeActivity extends BaseActivity {


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.VIBRATE,
                Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.ACCESS_WIFI_STATE,
                "com.bw.tech.permission.JPUSH_MESSAGE"
        }, 0);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public Object initPresenter() {
        return null;
    }
    int i=3;
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            if(msg==0){
//                if(i>0){
//                    i--;
//                    handler.sendEmptyMessage(0,1000);
//                }else{
//                    Intent intent=new Intent(WelcomeActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }else{
//                handler.sendEmptyMessageDelayed(0,1000);
//            }
//        }
//    };

}
