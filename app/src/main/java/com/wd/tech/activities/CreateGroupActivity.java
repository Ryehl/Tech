package com.wd.tech.activities;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.ActCreateGroupPresenter;

public class CreateGroupActivity extends BaseActivity<ActCreateGroupPresenter> {

    @Override
    public void initView() {
        //find view by id
    }

    @Override
    public void initData() {
        //doing
    }

    @Override
    public int getLayout() {
        return R.layout.activity_creatgroup;
    }

    @Override
    public ActCreateGroupPresenter initPresenter() {
        return new ActCreateGroupPresenter();
    }
}
