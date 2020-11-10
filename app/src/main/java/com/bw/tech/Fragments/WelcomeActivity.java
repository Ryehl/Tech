package com.bw.tech.Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.MainActivity;
import com.bw.tech.R;

public class WelcomeActivity extends BaseActivity {


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Intent intent=new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
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
