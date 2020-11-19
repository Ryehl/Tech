package com.wd.tech.activities;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.bean.ConstantMMkv;
import com.wd.tech.R;
import com.wd.tech.presenters.SettingPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mmkv.MMKV;

public class SettingActivity extends BaseActivity<SettingPresenter> {
    TextView setting_nickName,setting_sex,setting_birthday,setting_phone,setting_email;
    TextView setting_integral,setting_VIP,setting_Face,setting_wechat,setting_goout,setting_alterpwd;
    SimpleDraweeView setting_head;
    ImageView setting_next;
    @Override
    public void initView() {
        setting_nickName=findViewById(R.id.setting_nickName);
        setting_sex=findViewById(R.id.setting_sex);
        setting_next=findViewById(R.id.setting_next);
        setting_head=findViewById(R.id.setting_head);
        setting_birthday=findViewById(R.id.setting_birthday);
        setting_phone=findViewById(R.id.setting_phone);
        setting_email=findViewById(R.id.setting_email);
        setting_VIP=findViewById(R.id.setting_vip);
        setting_Face=findViewById(R.id.setting_face);
        setting_goout=findViewById(R.id. setting_goout);
        setting_alterpwd=findViewById(R.id.setting_alterpwd);
    }

    @Override
    public void initData() {
        //拿到MMKV中存储的值
        MMKV mmkv=MMKV.defaultMMKV();
        String nickName = mmkv.decodeString("nickName");
        String phone = mmkv.decodeString("phone");
//        String head=mmkv.decodeString("headPic");
        int whetherVip = mmkv.decodeInt("whetherVip");
        if(whetherVip==1){
            setting_VIP.setText("是");
        }else{
            setting_VIP.setText("否");
        }
        int whetherFaceId = mmkv.decodeInt("whetherFaceId");
        if(whetherFaceId==1){
            setting_Face.setText("已绑定");
        }else{
            setting_Face.setText("立即绑定");
        }

//        Log.i("img",head);
//        Uri img=Uri.parse(head);
        //设置值
        setting_nickName.setText(nickName);
//        setting_head.setImageURI(img);

        setting_phone.setText(phone);

        //退出登录
        setting_goout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,MainActivity.class);
                MMKV mmkv1=MMKV.defaultMMKV();
                mmkv1.putBoolean(ConstantMMkv.Key_IsLogin,false);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public SettingPresenter initPresenter() {
        return new SettingPresenter();
    }
}
