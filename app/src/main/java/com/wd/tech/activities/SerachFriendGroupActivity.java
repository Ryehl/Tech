package com.wd.tech.activities;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.ActSerachFriendGroupActivity;

public class SerachFriendGroupActivity extends BaseActivity<ActSerachFriendGroupActivity> {

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_serach_friend_group;
    }

    @Override
    public ActSerachFriendGroupActivity initPresenter() {
        return new ActSerachFriendGroupActivity();
    }
}
