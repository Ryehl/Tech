package com.wd.tech.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.adapters.CollectAdapter;
import com.wd.tech.beans.CollectBean;
import com.wd.tech.presenters.CollectPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends BaseActivity<CollectPresenter> {

    private RecyclerView collect_recyclerView;
    private List<CollectBean.ResultBean> list = new ArrayList<>();
    private CollectAdapter collectAdapter;

    @Override
    public void initView() {
        collect_recyclerView = findViewById(R.id.collect_recycle);
    }

    @Override
    public void initData() {
        pre.getCollectData();
    }

    public void CollectData(String json) {
        //解析
        CollectBean collectBean = new Gson().fromJson(json, CollectBean.class);
        list.addAll(collectBean.getResult());
        collectAdapter = new CollectAdapter(list);
        collect_recyclerView.setAdapter(collectAdapter);
        collect_recyclerView.setLayoutManager(new LinearLayoutManager(CollectActivity.this));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_collect;
    }

    @Override
    public CollectPresenter initPresenter() {
        return new CollectPresenter();
    }
}
