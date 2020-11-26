package com.wd.tech.activities;

import android.content.Intent;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.ActAddfriendPresenter;

public class AddfriendActivity extends BaseActivity<ActAddfriendPresenter> {

    @Override
    public void initView() {
        //
    }

    @Override
    public void initData() {
        //
        Intent intent = getIntent();
        int friendUid = intent.getIntExtra("friendUid", -1);
        
    }

    @Override
    public int getLayout() {
        return R.layout.activity_addfriend;
    }

    @Override
    public ActAddfriendPresenter initPresenter() {
        return new ActAddfriendPresenter();
    }
}
