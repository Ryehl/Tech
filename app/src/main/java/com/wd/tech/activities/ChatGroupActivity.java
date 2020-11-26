package com.wd.tech.activities;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.ActChatGroupPresenter;

public class ChatGroupActivity extends BaseActivity<ActChatGroupPresenter> {

    private int groupId;

    @Override
    public void initView() {
        //TODO layout fid
    }

    @Override
    public void initData() {
        //TODO
        groupId = getIntent().getIntExtra("groupId", -1);
        if (groupId == -1)
            return;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_chat_group;
    }

    @Override
    public ActChatGroupPresenter initPresenter() {
        return new ActChatGroupPresenter();
    }
}
