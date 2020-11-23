package com.wd.tech.activities;

import android.content.Intent;

import android.util.Log;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.mylibrary.utils.Utils;
import com.wd.tech.R;
import com.wd.tech.beans.DetailsBean;
import com.wd.tech.fragments.DetailsHasperFrag;
import com.wd.tech.fragments.DetailsNoReadPerFrag;
import com.wd.tech.presenters.DetailsPresenter;
import com.google.gson.Gson;

public class DetailsActivity extends BaseActivity<DetailsPresenter> {

    private final String TAG = "DetailsActivity";

    private RelativeLayout rel;
    private TextView tv_title;
    private TextView tv_time;
    private TextView tv_author;

    @Override
    public void initView() {
        rel = findViewById(R.id.details_rel);
        tv_title = findViewById(R.id.details_text_title);
        tv_time = findViewById(R.id.details_text_time);
        tv_author = findViewById(R.id.details_text_author);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        pre.getDetailsData(id);
        pre.getDetailsCommentData(id);
    }

    public void DetailsData(String json) {
        Log.d(TAG, "DetailsData: " + json);
        //解析
        DetailsBean detailsBean = new Gson().fromJson(json, DetailsBean.class);
        //设置标、作者和时间等
        DetailsBean.ResultBean result = detailsBean.getResult();
        String title = result.getTitle();
        String time = TypeConversionUtils.long2String(result.getReleaseTime());
        //本文转自。。。
        String source = result.getSource();
        tv_title.setText(title);
        tv_time.setText(time);
        tv_author.setText(source);
        //是否需要用户进行付费
        int readPower = result.getReadPower();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (readPower == 2) {
            //需要进行付费
            String summary = result.getSummary();
            ft.replace(R.id.details_rel, new DetailsNoReadPerFrag(summary));
        } else {
            //用户可以进行查看
            String data = result.getContent();
            ft.replace(R.id.details_rel, new DetailsHasperFrag(data));
        }
        ft.commit();
    }

    public void DetailsCommentData(String json) {
        //解析
        Log.d(TAG, "DetailsCommentData: " + json);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    public DetailsPresenter initPresenter() {
        return new DetailsPresenter();
    }
}
