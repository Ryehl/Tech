package com.wd.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.AlterSignaturePresenter;

public class AlterSignatureActivity extends BaseActivity<AlterSignaturePresenter> {


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

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
