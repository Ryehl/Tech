package com.wd.tech.activities;

import android.widget.ImageView;
import android.widget.TextView;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.SettingPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mmkv.MMKV;

public class SettingActivity extends BaseActivity<SettingPresenter> {
    TextView setting_nickName,setting_sex,setting_birthday,setting_phone,setting_email;
    TextView setting_integral,setting_VIP,setting_Face,setting_wechat,setting_goout,setting_alter;
    SimpleDraweeView setting_head;
    ImageView setting_next;
    @Override
    public void initView() {
        setting_nickName=findViewById(R.id.setting_nickName);
        setting_sex=findViewById(R.id.setting_sex);
        setting_next=findViewById(R.id.setting_next);
        setting_head=findViewById(R.id.setting_head);
    }

    @Override
    public void initData() {
        MMKV mmkv=MMKV.defaultMMKV();
        String nickName = mmkv.decodeString("nickName");

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
