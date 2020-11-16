package com.bw.tech.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.tech.R;
import com.bw.tech.adapters.NoticeAdapter;
import com.bw.tech.beans.NoticeBean;
import com.bw.tech.presenters.NoticePresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class NoticeActivity extends BaseActivity<NoticePresenter> {

    private RecyclerView notice_recycle;
    private NoticeAdapter noticeAdapter;
    private List<NoticeBean.ResultBean> list=new ArrayList<>();
    @Override
    public void initView() {
        notice_recycle=findViewById(R.id.notice_recycle);
    }

    @Override
    public void initData() {
        pre.getNoticeData();
    }
    public void NoticeData(String json){
        //解析
        NoticeBean noticeBean=new Gson().fromJson(json,NoticeBean.class);
        list.addAll(noticeBean.getResult());
        noticeAdapter=new NoticeAdapter(list);
        notice_recycle.setAdapter(noticeAdapter);
        notice_recycle.setLayoutManager(new LinearLayoutManager(NoticeActivity.this));
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public NoticePresenter initPresenter() {
        return null;
    }
}
