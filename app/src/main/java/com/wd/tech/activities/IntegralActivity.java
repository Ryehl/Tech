package com.wd.tech.activities;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tencent.mmkv.MMKV;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.adapters.IntegralRecordAdapter;
import com.wd.tech.beans.IntegralBean;
import com.wd.tech.beans.IntegralRecordBean;
import com.wd.tech.presenters.IntegralPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class IntegralActivity extends BaseActivity<IntegralPresenter> {

    private RecyclerView integralrecord_recycle;
    private List<IntegralRecordBean.ResultBean> list = new ArrayList<>();
    private IntegralRecordAdapter integralRecordAdapter;
    TextView integral_num;

    @Override
    public void initView() {
        integral_num = findViewById(R.id.integral_num);
        integralrecord_recycle = findViewById(R.id.myintegralrecod_recycle);
    }

    @Override
    public void initData() {
        pre.getIntegralData();
        pre.getIntegralRecordData();
    }

    public void IntegralData(String json) {
        //解析数据
        IntegralBean integralBean = new Gson().fromJson(json, IntegralBean.class);
        integral_num.setText(integralBean.getResult().getAmount() + "");

        MMKV mmkv=MMKV.defaultMMKV();
        mmkv.putInt("integral_num",integralBean.getResult().getAmount());
    }

    public void IntegralRecordData(String json) {
        //解析
        IntegralRecordBean integralRecordBean = new Gson().fromJson(json, IntegralRecordBean.class);
        list.addAll(integralRecordBean.getResult());
        integralRecordAdapter = new IntegralRecordAdapter(list);
        integralrecord_recycle.setAdapter(integralRecordAdapter);
        integralrecord_recycle.setLayoutManager(new LinearLayoutManager(IntegralActivity.this));
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
