package com.wd.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.beans.AlterSignatureBean;
import com.wd.tech.presenters.AlterSignaturePresenter;

public class AlterSignatureActivity extends BaseActivity<AlterSignaturePresenter> {

    private EditText alter_signature;
    private Button submit_signature;
    private String signature;
    @Override
    public void initView() {
        alter_signature=findViewById(R.id.alter_signature);
        submit_signature=findViewById(R.id.submit_signature);
    }

    @Override
    public void initData() {
        submit_signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //拿到修改后的签名
                 signature=alter_signature.getText().toString();
                pre.getAlterSignatureData(signature);

            }
        });
    }
    //修改签名
    public void AlterSignatureData(String json){
        //解析
        AlterSignatureBean alterSignatureBean=new Gson().fromJson(json,AlterSignatureBean.class);
        Toast.makeText(this, alterSignatureBean.getMessage(), Toast.LENGTH_SHORT).show();
        if(alterSignatureBean.getMessage().equals("修改成功")){
            MMKV mmkv=MMKV.defaultMMKV();
            mmkv.putString("signature",signature);
        }
    }
    @Override
    public int getLayout() {
        return R.layout.activity_alter_signature;
    }

    @Override
    public AlterSignaturePresenter initPresenter() {
        return new AlterSignaturePresenter();
    }
}
