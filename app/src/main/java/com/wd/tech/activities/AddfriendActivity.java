package com.wd.tech.activities;

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
