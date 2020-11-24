package com.wd.tech.activities;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.bean.ConstantMMkv;
import com.wd.tech.R;
import com.wd.tech.diyview.BottomSelectImage;
import com.wd.tech.presenters.SettingPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mmkv.MMKV;

public class SettingActivity extends BaseActivity<SettingPresenter> {
    RelativeLayout setting_sex;
    TextView setting_nickName, setting_birthday, setting_phone, setting_email, setting_sex1;
    TextView setting_integral, setting_VIP, setting_Face, setting_wechat, setting_goout, setting_alterpwd;
    SimpleDraweeView setting_head;
    ImageView setting_next,setting_back;

    @Override
    public void initView() {
        setting_nickName = findViewById(R.id.setting_nickName);
        setting_sex = findViewById(R.id.setting_sex);
        setting_next = findViewById(R.id.setting_next);
        setting_head = findViewById(R.id.setting_head);
        setting_birthday = findViewById(R.id.setting_birthday);
        setting_phone = findViewById(R.id.setting_phone);
        setting_email = findViewById(R.id.setting_email);
        setting_VIP = findViewById(R.id.setting_vip);
        setting_Face = findViewById(R.id.setting_face);
        setting_goout = findViewById(R.id.setting_goout);
        setting_alterpwd = findViewById(R.id.setting_alterpwd);
        setting_integral = findViewById(R.id.setting_integral);
        setting_sex1 = findViewById(R.id.setting_sex1);
        setting_back=findViewById(R.id.setting_back);
    }

    @Override
    public void initData() {
        //拿到MMKV中存储的值
        MMKV mmkv = MMKV.defaultMMKV();
        String nickName = mmkv.decodeString("nickName");
        String phone = mmkv.decodeString("phone");
//        String head=mmkv.decodeString("headPic");
        int whetherVip = mmkv.decodeInt("whetherVip");
        if (whetherVip == 1) {
            setting_VIP.setText("是");
        } else {
            setting_VIP.setText("否");
        }
        int whetherFaceId = mmkv.decodeInt("whetherFaceId");
        if (whetherFaceId == 1) {
            setting_Face.setText("已绑定");
        } else {
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
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                MMKV mmkv1 = MMKV.defaultMMKV();
                mmkv1.putBoolean(ConstantMMkv.Key_IsLogin, false);
                startActivity(intent);
            }
        });
        //签名
        setting_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivity.this, AlterSignatureActivity.class);
                startActivity(intent);
            }
        });
        //TODO 性别
        setting_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹框
                BottomSheetDialog bsd = new BottomSheetDialog(SettingActivity.this);
                bsd.setCancelable(true);
                bsd.setContentView(R.layout.activity_sheetdialog);
                //弹出(显示出来所加载的布局效果)
                bsd.show();
            }
        });
        //接收性别选择页面传过来的值
        Intent intent = getIntent();
        String man = intent.getStringExtra("sex");
        setting_sex1.setText(man);

        //返回上一个页面
        setting_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
