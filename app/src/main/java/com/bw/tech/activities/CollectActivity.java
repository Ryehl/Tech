package com.bw.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.R;
import com.bw.tech.presenters.CollectPresenter;

public class CollectActivity extends BaseActivity<CollectPresenter> {


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_collect;
    }

    @Override
    public CollectPresenter initPresenter() {
        return new CollectPresenter();
    }
}
