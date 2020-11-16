package com.bw.tech.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.MyApp;
import com.bw.tech.R;
import com.bw.tech.adapters.InterestAdapter;
import com.bw.tech.beans.InterestBean;
import com.bw.tech.presenters.InterestPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class InterestActivity extends BaseActivity<InterestPresenter> {

    private RecyclerView recyclerView;
    private List<InterestBean.ResultBean> list=new ArrayList<>();
    private InterestAdapter interestAdapter;
    @Override
    public void initView() {
        recyclerView=findViewById(R.id.interest_recycle);
    }

    @Override
    public void initData() {
        pre.getInterestData();
    }
    public void InterestData(String json){
        //解析
        InterestBean interestBean=new Gson().fromJson(json,InterestBean.class);
        list.addAll(interestBean.getResult());
        interestAdapter=new InterestAdapter(list);
        recyclerView.setAdapter(interestAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(InterestActivity.this,2));
    }
    @Override
    public int getLayout() {
        return R.layout.activity_interest;
    }

    @Override
    public InterestPresenter initPresenter() {
        return new InterestPresenter();
    }
}
