package com.bw.tech.activities;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.R;
import com.bw.tech.adapters.AttentionAdapter;
import com.bw.tech.beans.AttentionBean;
import com.bw.tech.presenters.AttentionPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AttentionActivity extends BaseActivity<AttentionPresenter> {

    private RecyclerView attention_recycle;
    private AttentionAdapter attentionAdapter;
    private List<AttentionBean.ResultBean> list=new ArrayList<>();
    @Override
    public void initView() {
        attention_recycle=findViewById(R.id.attention_recycle);
;    }

    @Override
    public void initData() {
        pre.getAttentionData();
    }
    public void AttentionData(String json){
        //解析
        AttentionBean attentionBean=new Gson().fromJson(json,AttentionBean.class);
        list.addAll(attentionBean.getResult());
        attentionAdapter=new AttentionAdapter(list);
        attention_recycle.setAdapter(attentionAdapter);
        attention_recycle.setLayoutManager(new LinearLayoutManager(AttentionActivity.this));
    }
    @Override
    public int getLayout() {
        return R.layout.activity_attention;
    }

    @Override
    public AttentionPresenter initPresenter() {
        return new AttentionPresenter();
    }
}
