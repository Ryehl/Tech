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

    private RecyclerView details_recycle, details_recycle_two;
    private List<DetailsBean.ResultBean> list = new ArrayList<>();
    private DetailsAdapter detailsAdapter;
    private List<DetailsBean.ResultBean.InformationListBean> list2 = new ArrayList<>();
    private List<DetailsBean.ResultBean> list_content = new ArrayList<>();
    private DetailsAdapter_Recommend detailsAdapter_recommend;
    private List<DetailsCommentBean.ResultBean> list_comment = new ArrayList<>();
    private DetailsAdapter_Comment detailsAdapter_comment;
    private WebView webView;

    @Override
    public void initView() {
        details_recycle = findViewById(R.id.details_recycle);
        webView = findViewById(R.id.details_webview);
        details_recycle_two = findViewById(R.id.details_recycle_two);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        pre.getDetailsData(id);
        pre.getDetailsCommentData(id);

//        detailsAdapter_comment.setOnDetailsComment(new DetailsAdapter_Comment.OnDetailsComment() {
//            @Override
//            public void DetailsComment(int index) {
//                pre.getDetailsCommentData(index);
//            }
//        });

    }

    public void DetailsData(String json) {
        //解析
        DetailsBean detailsBean = new Gson().fromJson(json, DetailsBean.class);
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
