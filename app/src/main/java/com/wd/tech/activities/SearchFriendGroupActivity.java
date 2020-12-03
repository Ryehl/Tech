package com.wd.tech.activities;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.ActSerachFriendGroupActivity;

public class SearchFriendGroupActivity extends BaseActivity<ActSerachFriendGroupActivity> {

    private EditText et_search;
    private TabLayout tab;
    private RecyclerView recy_show;
    public static final int FIREND = 753;
    public static final int GROUP = 106;

    @Override
    public void initView() {
        et_search = findViewById(R.id.searchfg_et_search);
        tab = findViewById(R.id.searchfg_tab);
        recy_show = findViewById(R.id.searchfg_recy_show);
        //bindService()
    }

    @Override
    public void initData() {
        initTab();
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //输入的字符串
                String input = s.toString();
                //获取数据
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * 初始化Tab
     */
    private void initTab() {
        Intent intent = getIntent();
        tab.addTab(tab.newTab().setText("好友"));
        tab.addTab(tab.newTab().setText("群聊"));
        int type = intent.getIntExtra("TYPE", -1);
        if (type == -1)
            return;
        if (type == GROUP)
            tab.getTabAt(1).select();
         else
            tab.getTabAt(0).select();
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
