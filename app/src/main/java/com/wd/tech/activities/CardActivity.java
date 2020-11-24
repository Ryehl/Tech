package com.wd.tech.activities;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.adapters.CardAdapter;
import com.wd.tech.beans.CardBean;
import com.wd.tech.presenters.CardPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CardActivity extends BaseActivity<CardPresenter> {

    private RecyclerView mycard_recyclerView;
    private CardAdapter cardAdapter;
    private ImageView card_back;
    private List<CardBean.ResultBean> list = new ArrayList<>();

    @Override
    public void initView() {
        mycard_recyclerView = findViewById(R.id.mycard_recycle);
        card_back=findViewById(R.id.card_back);
    }

    @Override
    public void initData() {
        pre.getCardData();

        //点击返回上个页面
        card_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void CardData(String json) {
        //解析数据
        CardBean cardBean = new Gson().fromJson(json, CardBean.class);
        list.addAll(cardBean.getResult());
        cardAdapter = new CardAdapter(list);
        mycard_recyclerView.setAdapter(cardAdapter);
        mycard_recyclerView.setLayoutManager(new LinearLayoutManager(CardActivity.this));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_card;
    }

    @Override
    public CardPresenter initPresenter() {
        return new CardPresenter();
    }
}
