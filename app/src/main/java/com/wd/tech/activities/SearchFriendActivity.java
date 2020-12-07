package com.wd.tech.activities;

import android.content.Intent;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.beans.JsonFriendInfoByPhoneBean;
import com.wd.tech.presenters.ActSerachFriendActivity;

import java.util.regex.Pattern;

public class SearchFriendActivity extends BaseActivity<ActSerachFriendActivity> {

    private EditText et_search;
    private TextView tv_toSearch;
    private RelativeLayout rel;
    private SimpleDraweeView sdv_hd;
    private TextView tv_name;

    @Override
    public void initView() {
        et_search = findViewById(R.id.searchfg_et_search);
        tv_toSearch = findViewById(R.id.searchfg_tv);
        rel = findViewById(R.id.searchfg_rel);
        sdv_hd = findViewById(R.id.item_friendphone_sdv_hd);
        tv_name = findViewById(R.id.item_firendphone_tv_name);
    }

    @Override
    public void initData() {
        tv_toSearch.setOnClickListener(v -> {
            String input = et_search.getText().toString().trim();
            if (checkPhone(input)) {
                pre.searchFirend(input);
            } else {
                Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 成功搜索到信息后进行展示
     *
     * @param json
     */
    public void showFriends(String json) {
        JsonFriendInfoByPhoneBean bean = new Gson().fromJson(json, JsonFriendInfoByPhoneBean.class);
        if (bean == null)
            return;
        JsonFriendInfoByPhoneBean.ResultBean result = bean.getResult();
        if (result == null) {
            Toast.makeText(this, "没有搜索到用户", Toast.LENGTH_SHORT).show();
        } else {
            GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                    .setRoundingParams(RoundingParams.asCircle())
                    .build();
            sdv_hd.setHierarchy(hierarchy);
            sdv_hd.setImageURI(result.getHeadPic());
            tv_name.setText(result.getNickName());
            rel.setOnClickListener(v -> {
                //点击跳转到加好友页面
                Intent intent = new Intent(this, ContactInfoActivity.class);
                intent.putExtra("friendUid", result.getUserId());
                startActivity(intent);
            });
        }
    }

    private boolean checkPhone(String input) {
        if (input == null)
            return false;
        if (input.length() != 11)
            return false;
        return Pattern.matches("^1[356789]\\d{9}$", input);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search_friend_group;
    }

    @Override
    public ActSerachFriendActivity initPresenter() {
        return new ActSerachFriendActivity();
    }
}
