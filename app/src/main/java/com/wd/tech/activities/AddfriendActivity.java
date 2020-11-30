package com.wd.tech.activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.ActAddfriendPresenter;

public class AddfriendActivity extends BaseActivity<ActAddfriendPresenter> {

    private SimpleDraweeView sdv_hd;
    private TextView tv_friendName;
    private EditText et_remark;
    private Button btn_add;

    @Override
    public void initView() {
        //find view by id
        sdv_hd = findViewById(R.id.addfriend_sdv_hd);
        tv_friendName = findViewById(R.id.addfriend_tv_friendName);
        et_remark = findViewById(R.id.addfriend_et_remark);
        btn_add = findViewById(R.id.addfriend_btn_add);
    }

    @Override
    public void initData() {
        //通过intent获取数据
        Intent intent = getIntent();
        int friendUid = intent.getIntExtra("friendUid", -1);
        if (friendUid == -1) {
            finish();
            return;
        }
        //TODO 展示要添加的好友信息
        btn_add.setOnClickListener(v -> {
            //添加服务器好友
            pre.addFriend();
        });
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
