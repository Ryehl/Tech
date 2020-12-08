package com.wd.tech.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.adapters.FriendNoticeAdap;
import com.wd.tech.beans.JsonFriendReqNotBean;
import com.wd.tech.presenters.ActFriendRequestNoticePresenter;

/**
 * 好友申请通知
 *
 * @author Xaoyv
 * data 2020年12月7日 16点53分
 */
public class FriendRequestNoticeActivity extends BaseActivity<ActFriendRequestNoticePresenter> {

    private RecyclerView recy;

    @Override
    public void initView() {
        recy = findViewById(R.id.friend_reqnot_recy);
    }

    @Override
    public void initData() {
        pre.getNotices();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_friend_request_notice;
    }

    @Override
    public ActFriendRequestNoticePresenter initPresenter() {
        return new ActFriendRequestNoticePresenter();
    }

    public void showNotic(String json) {
        JsonFriendReqNotBean bean = new Gson().fromJson(json, JsonFriendReqNotBean.class);
        recy.setAdapter(new FriendNoticeAdap(bean.getResult()));
        recy.setLayoutManager(new LinearLayoutManager(this));
    }
}
