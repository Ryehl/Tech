package com.bw.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.R;
import com.bw.tech.presenters.DetailsPresenter;

public class DetailsActivity extends BaseActivity<DetailsPresenter> {

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    public DetailsPresenter initPresenter() {
        return new DetailsPresenter();
    }
}
