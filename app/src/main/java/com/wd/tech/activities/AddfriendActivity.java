package com.wd.tech.activities;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.beans.JsonFriendInfoBean;
import com.wd.tech.beans.JsonMsgAndStatusBean;
import com.wd.tech.presenters.ActAddfriendPresenter;
import com.wd.tech.utils.JIMUtils;

import cn.jpush.im.api.BasicCallback;

public class AddfriendActivity extends BaseActivity<ActAddfriendPresenter> {

    private SimpleDraweeView sdv_hd;
    private TextView tv_friendName;
    private EditText et_remark;
    private Button btn_add;

    private String userName;
    private String TAG = getClass().getName();

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
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setRoundingParams(RoundingParams.asCircle())//圆形图
                .build();
        sdv_hd.setHierarchy(hierarchy);
        pre.getFriendInfo(friendUid);
        btn_add.setOnClickListener(v -> {
            String remark = et_remark.getText().toString().trim();
            if (remark.length() == 0) {
                Toast.makeText(this, "请输入验证信息", Toast.LENGTH_SHORT).show();
                return;
            }
            if (userName == null) {
                Toast.makeText(this, "好友信息加载中，请稍后", Toast.LENGTH_SHORT).show();
                return;
            }
            //添加服务器好友
            pre.addFriend(friendUid, remark);
            //添加极光好友
            JIMUtils.getJimUtils().addFriend(userName, remark, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    Log.d(TAG, "gotResult: " + i + s);
                }
            });
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

    public void addCallback(JsonMsgAndStatusBean bean) {
        if (bean == null)
            return;
        Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        if ("0000".equals(bean.getStatus())) {
            finish();
        }
    }

    public void setFriendInfo(String json) {
        JsonFriendInfoBean friendInfoBean = new Gson().fromJson(json, JsonFriendInfoBean.class);
        if (friendInfoBean == null)
            return;
        if (friendInfoBean.getResult() == null)
            return;
        tv_friendName.setText(friendInfoBean.getResult().getNickName());
        userName = friendInfoBean.getResult().getUserName();
    }
}
