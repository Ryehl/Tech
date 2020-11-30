package com.wd.tech.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.utils.ViewUtils;
import com.wd.tech.R;

public class WelcomeActivity extends BaseActivity {

    private final int REQUEST_CODE = 0;
    private int countdown = 4;
    private TextView welcome_tv_countdown;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (countdown > 0) {
                countdown--;
                welcome_tv_countdown.setText(String.valueOf(countdown));
                handler.sendEmptyMessageDelayed(0, 1000);
            } else
                skip();
        }
    };

    @Override
    public void initView() {
        welcome_tv_countdown = findViewById(R.id.welcome_tv_countdown);
    }

    @Override
    public void initData() {
        ViewUtils.setViewTransparent(this);
        //requestPermissions
        reqPre();

        //start handler
        handler.sendEmptyMessage(0);

        //set on click listener
        welcome_tv_countdown.setOnClickListener(v -> skip());
    }

    /**
     * 跳转新的界面
     */
    private void skip() {
        //跳转
        handler.removeCallbacksAndMessages(null);

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else
            reqPre();
    }

    /**
     * request permissions
     */
    private void reqPre() {
        requestPermissions(new String[]{
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
        }, REQUEST_CODE);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public Object initPresenter() {
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length == 0)
                    return;
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (countdown <= 0)
                        skip();
                }
                break;
        }
    }
}
