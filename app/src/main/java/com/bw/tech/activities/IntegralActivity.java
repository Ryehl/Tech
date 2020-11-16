package com.bw.tech.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.R;
import com.bw.tech.adapters.IntegralAdapter;
import com.bw.tech.beans.IntegralBean;
import com.bw.tech.presenters.IntegralPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class IntegralActivity extends BaseActivity<IntegralPresenter> {

    private RecyclerView integral_recycle,integralrecord_recycle;
    private IntegralAdapter integralAdapter;
    private List<IntegralBean.ResultBean> list=new ArrayList<>();
    @Override
    public void initView() {
        integral_recycle=findViewById(R.id.integral_recycle);
    }

    @Override
    public void initData() {
        pre.getIntegralData();
    }
    public void IntegralData(String json){
        //解析数据
        IntegralBean integralBean=new Gson().fromJson(json,IntegralBean.class);
        list.add(integralBean.getResult());
        integralAdapter=new IntegralAdapter(list);
        integral_recycle.setAdapter(integralAdapter);
        integral_recycle.setLayoutManager(new LinearLayoutManager(IntegralActivity.this));
    }
    @Override
    public int getLayout() {
        return R.layout.activity_integral;
    }

    @Override
    public IntegralPresenter initPresenter() {
        return new IntegralPresenter();
    }
}
