package com.bw.tech.activities;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.R;
import com.bw.tech.fragments.ActCreateGroupPresenter;

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
