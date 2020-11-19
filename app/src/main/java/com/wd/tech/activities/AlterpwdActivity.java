package com.wd.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.AlterpwdPresenter;

public class AlterpwdActivity extends BaseActivity<AlterpwdPresenter> {


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_alterpwd;
    }

    @Override
    public AlterpwdPresenter initPresenter() {
        return new AlterpwdPresenter();
    }
}
