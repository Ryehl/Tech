package com.wd.tech.activities;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.beans.JsonMsgAndStatusBean;
import com.wd.tech.presenters.ActJoinGroupPresenter;

/**
 * 加入群聊
 */
public class JoinGroupActivity extends BaseActivity<ActJoinGroupPresenter> {

    private int groupId;
    private SimpleDraweeView sdv_groupHd;
    private EditText et_inputRemark;
    private Button btn;

    @Override
    public void initView() {
        sdv_groupHd = findViewById(R.id.addgroup_sdv_groupHd);
        et_inputRemark = findViewById(R.id.addgroup_et_remark);
        btn = findViewById(R.id.addgroup_btn_add);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        groupId = intent.getIntExtra("groupId", -1);
        String groupHd = intent.getStringExtra("groupHd");
        //展示圆形
        sdv_groupHd.setHierarchy(new GenericDraweeHierarchyBuilder(getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build());
        sdv_groupHd.setImageURI(groupHd);
        if (groupId == -1)
            return;
        btn.setOnClickListener(v -> {
            String info = et_inputRemark.getText().toString().trim();
            if (info.length() == 0) {
                Toast.makeText(this, "请输入验证信息", Toast.LENGTH_SHORT).show();
                return;
            }
            pre.joinGroup(groupId);
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_join_group;
    }

    @Override
    public ActJoinGroupPresenter initPresenter() {
        return new ActJoinGroupPresenter();
    }

    public void joinGroupSuccess(String json) {
        JsonMsgAndStatusBean msgAndStatusBean = new Gson().fromJson(json, JsonMsgAndStatusBean.class);
        Log.d("TAG", "joinGroupSuccess: " + msgAndStatusBean);
        Toast.makeText(this, "申请成功，请等待群主或管理员同意", Toast.LENGTH_SHORT).show();
        finish();
    }
}
