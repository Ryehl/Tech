package com.wd.tech.activities;

import android.content.Intent;

import android.util.Log;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.adapters.DetailsAdapter_Comment;
import com.wd.tech.adapters.DetailsAdapter_Recommend;
import com.wd.tech.adapters.DetailsAdapter_Comment;
import com.wd.tech.beans.DetailsBean;
import com.wd.tech.beans.JsonDetailsCommentsBean;
import com.wd.tech.beans.DetailsCommentBean;
import com.wd.tech.fragments.DetailsHasperFrag;
import com.wd.tech.fragments.DetailsNoReadPerFrag;
import com.wd.tech.presenters.DetailsPresenter;
import com.google.gson.Gson;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends BaseActivity<DetailsPresenter> {

    private final String TAG = "DetailsActivity";
    private RelativeLayout rel;
    private RecyclerView recy_comment;
    private RecyclerView recy_infomation;
    private TextView tv_title;
    private TextView tv_time;
    private TextView tv_author;
    private DetailsAdapter_Recommend detailsAdapter_recommend;
    @Override
    public void initView() {
        rel = findViewById(R.id.details_rel);
        recy_comment = findViewById(R.id.details_recycle_comments);
        recy_infomation = findViewById(R.id.details_recycle_horizontal_images);
        tv_title = findViewById(R.id.details_text_title);
        tv_time = findViewById(R.id.details_text_time);
        tv_author = findViewById(R.id.details_text_author);
    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        //获取资讯详情
        pre.getDetailsData(id);
        //获取资讯评论
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

        //set adapter
        List<DetailsBean.ResultBean.InformationListBean> informationList = detailsBean.getResult().getInformationList();
        if (informationList == null)
            return;
        detailsAdapter_recommend=new DetailsAdapter_Recommend(informationList);
        recy_infomation.setAdapter(detailsAdapter_recommend);
        recy_infomation.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        //接口回调跳转到详情页
        detailsAdapter_recommend.setRecommendDetails(new DetailsAdapter_Recommend.RecommendDetails() {
            @Override
            public void recommend(int index) {
                Intent intent=new Intent(DetailsActivity.this,DetailsActivity.class);
                intent.putExtra("id",index);
                startActivity(intent);
            }
        });

    }

    /**
     * 设置评论列表
     *
     * @param json
     */
    public void DetailsCommentData(String json) {
        //解析
        JsonDetailsCommentsBean commentsBean = new Gson().fromJson(json, JsonDetailsCommentsBean.class);
        //setAdapter
        DetailsAdapter_Comment adapter = new DetailsAdapter_Comment(commentsBean.getResult());
        recy_comment.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recy_comment.setLayoutManager(layout);
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
