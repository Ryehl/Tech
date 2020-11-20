package com.wd.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mmkv.MMKV;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.PersonalInformationPresenter;

public class PersonalInformationActivity extends BaseActivity<PersonalInformationPresenter> {

    private TextView personal_finish,personal_nickName;
    private RelativeLayout personal_sex,personal_birthday,personal_email;
    private ImageView personal_back,personal_signature;
    @Override
    public void initView() {
        personal_nickName=findViewById(R.id.personal_nickName);
        personal_finish=findViewById(R.id.personal_finish);
        personal_sex=findViewById(R.id.personal_sex);
        personal_birthday=findViewById(R.id.personal_birthday);
        personal_email=findViewById(R.id.personal_email);
        personal_back=findViewById(R.id.personal_back);
        personal_signature=findViewById(R.id.personal_signature);
    }

    @Override
    public void initData() {
        pre.getPersonalInformationData();
        MMKV mmkv=MMKV.defaultMMKV();
        String nickName = mmkv.decodeString("nickName");
        personal_nickName.setText(nickName);
        //签名
//        personal_signature.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(PersonalInformationActivity.this,AlterSignatureActivity.class);
//                startActivity(intent);
//            }
//        });


    }
    public void PersonalInformation(String json){

    }

    @Override
    public int getLayout() {
        return R.layout.activity_personal_information;
    }

    @Override
    public PersonalInformationPresenter initPresenter() {
        return new PersonalInformationPresenter();
    }
}
