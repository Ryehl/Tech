package com.wd.tech.activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.beans.JsonGroupInfoBean;
import com.wd.tech.beans.JsonIsFriendBean;
import com.wd.tech.presenters.ActGroupInfoPresenter;

public class GroupInfoActivity extends BaseActivity<ActGroupInfoPresenter> {

    private SimpleDraweeView sdv_groupHd;
    private TextView tv_groupName;
    private TextView tv_groupDescription;
    private TextView tv_groupCount;
    private Button btn;
    private int groupId = -1;
    private String groupImage;

    @Override
    public void initView() {
        sdv_groupHd = findViewById(R.id.groupinfo_sdv_groupHd);
        tv_groupName = findViewById(R.id.groupinfo_tv_groupName);
        tv_groupDescription = findViewById(R.id.groupinfo_tv_description);
        tv_groupCount = findViewById(R.id.groupinfo_tv_count);
        btn = findViewById(R.id.groupinfo_btn);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        groupId = intent.getIntExtra("groupId", -1);
        if (groupId == -1)
            return;
        pre.checkInGroup(groupId);
        pre.getGroupInfo(groupId);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_group_info;
    }

    @Override
    public ActGroupInfoPresenter initPresenter() {
        return new ActGroupInfoPresenter();
    }

    public void setData(String json) {
        JsonGroupInfoBean groupInfoBean = new Gson().fromJson(json, JsonGroupInfoBean.class);
        if (groupInfoBean == null || groupInfoBean.getResult() == null)
            return;
        JsonGroupInfoBean.ResultBean result = groupInfoBean.getResult();
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        sdv_groupHd.setHierarchy(hierarchy);
        groupImage = result.getGroupImage();
        sdv_groupHd.setImageURI(groupImage);
        tv_groupName.setText(result.getGroupName());
        tv_groupDescription.setText(result.getDescription());
        tv_groupCount.setText(result.getCurrentCount() + "/" + result.getMaxCount());
    }

    public void setBtn(String json) {
        //复用bean类
        JsonIsFriendBean inGroupBean = new Gson().fromJson(json, JsonIsFriendBean.class);
        if (inGroupBean == null)
            return;
        if (inGroupBean.getFlag() == 1) {
            //在群里--点击进入群聊
            btn.setText("进入聊天");
            btn.setOnClickListener(v -> {
                Intent in = new Intent(this, ChatGroupActivity.class);
                in.putExtra("groupId", groupId);
                startActivity(in);
            });
        } else {
            btn.setText("加入群聊");
            btn.setOnClickListener(v -> {
                //跳转到添加群聊页面
                Intent intent = new Intent(this, JoinGroupActivity.class);
                intent.putExtra("groupId", groupId);
                intent.putExtra("groupHd", groupImage);
                startActivity(intent);
            });
        }
    }
}
