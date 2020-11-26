package com.wd.tech.activities;

import android.content.Intent;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.ActChatFriendPresenter;

public class ChatFriendActivity extends BaseActivity<ActChatFriendPresenter> {

    private int friendUid;
    private String userName;

    @Override
    public void initView() {
        //TODO fid
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        friendUid = intent.getIntExtra("friendUid", -1);
        userName = intent.getStringExtra("userName");
        //TODO
    }

    @Override
    public int getLayout() {
        return R.layout.activity_chat_friend;
    }

    @Override
    public ActChatFriendPresenter initPresenter() {
        return new ActChatFriendPresenter();
    }
}
