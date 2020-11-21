package com.wd.tech.activities;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.util.Log;
import android.webkit.WebView;


import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.utils.Utils;
import com.wd.tech.R;
import com.wd.tech.adapters.DetailsAdapter;
import com.wd.tech.adapters.DetailsAdapter_Comment;
import com.wd.tech.adapters.DetailsAdapter_Recommend;
import com.wd.tech.beans.DetailsBean;
import com.wd.tech.beans.DetailsCommentBean;
import com.wd.tech.presenters.DetailsPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends BaseActivity<DetailsPresenter> {

    private final String TAG = "DetailsActivity";

    private WebView webView;

    @Override
    public void initView() {
        webView = findViewById(R.id.details_webview);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        pre.getDetailsData(id);
        pre.getDetailsCommentData(id);
    }

    public void DetailsData(String json) {
        //解析
        DetailsBean detailsBean = new Gson().fromJson(json, DetailsBean.class);
        //
        String data = detailsBean.getResult().getContent();
        webView.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
        Utils.setttingWebView(webView);
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
