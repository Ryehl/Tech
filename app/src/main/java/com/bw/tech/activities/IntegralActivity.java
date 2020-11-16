package com.bw.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.R;
import com.bw.tech.presenters.IntegralPresenter;

public class IntegralActivity extends BaseActivity<IntegralPresenter> {


    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        pre.getIntegralData();
    }
    public void Integral(String json){

    }
    @Override
    public int getLayout() {
        return R.layout.activity_integral;
    }

    @Override
    public IntegralPresenter initPresenter() {
        return new IntegralPresenter();
    }
}
