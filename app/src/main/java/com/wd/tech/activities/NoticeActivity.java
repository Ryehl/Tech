package com.wd.tech.activities;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.adapters.NoticeAdapter;
import com.wd.tech.beans.NoticeBean;
import com.wd.tech.presenters.NoticePresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends BaseActivity<NoticePresenter> {

    private RecyclerView notice_recycle;
    private NoticeAdapter noticeAdapter;
    private ImageView notice_back;
    private List<NoticeBean.ResultBean> list = new ArrayList<>();

    @Override
    public void initView() {
        notice_recycle = findViewById(R.id.notice_recycle);
        notice_back=findViewById(R.id.notice_back);
    }

    @Override
    public void initData() {
        pre.getNoticeData();

        //返回上一页
        notice_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void NoticeData(String json) {
        //解析
        NoticeBean noticeBean = new Gson().fromJson(json, NoticeBean.class);
        list.addAll(noticeBean.getResult());
        noticeAdapter = new NoticeAdapter(list);
        notice_recycle.setAdapter(noticeAdapter);
        notice_recycle.setLayoutManager(new LinearLayoutManager(NoticeActivity.this));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_notice;
    }

    @Override
    public NoticePresenter initPresenter() {
        return new NoticePresenter();
    }
}
