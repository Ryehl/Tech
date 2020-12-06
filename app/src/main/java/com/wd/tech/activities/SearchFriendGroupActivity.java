package com.wd.tech.activities;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.ActSerachFriendGroupActivity;

import java.util.regex.Pattern;

public class SearchFriendGroupActivity extends BaseActivity<ActSerachFriendGroupActivity> {

    private EditText et_search;
    private TextView tv_toSearch;
    private RecyclerView recy_show;

    @Override
    public void initView() {
        et_search = findViewById(R.id.searchfg_et_search);
        tv_toSearch = findViewById(R.id.searchfg_tv);
        recy_show = findViewById(R.id.searchfg_recy_show);
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
    public ActSerachFriendGroupActivity initPresenter() {
        return new ActSerachFriendGroupActivity();
    }
}
