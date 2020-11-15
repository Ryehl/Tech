package com.bw.tech.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.MyApp;
import com.bw.tech.R;
import com.bw.tech.activities.PublishActivity;
import com.bw.tech.adapters.CommunityAdapter;
import com.bw.tech.beans.CommuntiyBean;
import com.bw.tech.presenters.FragCommuntityPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainCommuntityFrag extends BaseFragment<FragCommuntityPresenter> {

    RecyclerView recyclerView;
    private List<CommuntiyBean.ResultBean> list = new ArrayList<>();
    private CommunityAdapter communityAdapter;
    private SimpleDraweeView community_publish;
    @Override
    public void initView() {
        View view = getView();
        recyclerView = view.findViewById(R.id.community_recycle);
        community_publish=view.findViewById(R.id.publish);
    }

    @Override
    public void initData() {
        pre.CommunityData();

        //点击跳转到发表帖子页面
        community_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PublishActivity.class);
                startActivity(intent);
            }
        });
    }

    public void CommunityData(String json) {
        //解析
        CommuntiyBean communtiyBean = new Gson().fromJson(json, CommuntiyBean.class);
        list.addAll(communtiyBean.getResult());
        communityAdapter=new CommunityAdapter(list);
        recyclerView.setAdapter(communityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApp.context));
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_my_communtity;
    }

    @Override
    public FragCommuntityPresenter initPresenter() {
        return new FragCommuntityPresenter();
    }
}
