package com.bw.tech.activities;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.R;
import com.bw.tech.presenters.ActAddfriendPresenter;

public class AddfriendActivity extends BaseActivity<ActAddfriendPresenter> {

    @Override
    public void initView() {
        //
    }

    @Override
    public void initData() {
        //
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
