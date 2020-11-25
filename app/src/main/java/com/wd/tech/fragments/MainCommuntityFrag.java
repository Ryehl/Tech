package com.wd.tech.fragments;


import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;

import com.wd.mylibrary.base.BaseFragment;
import com.wd.tech.MyApp;
import com.wd.tech.R;
import com.wd.tech.activities.FindUserActivity;
import com.wd.tech.activities.PublishActivity;
import com.wd.tech.adapters.CommunityAdapter;
import com.wd.tech.adapters.CommunityCommentAdapter;
import com.wd.tech.beans.CommuntiyBean;
import com.wd.tech.presenters.FragCommuntityPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainCommuntityFrag extends BaseFragment<FragCommuntityPresenter> {

    RecyclerView recyclerView,comment_recycle;
    private List<CommuntiyBean.ResultBean> list = new ArrayList<>();
    private List<CommuntiyBean.ResultBean.CommunityCommentVoListBean> list2=new ArrayList<>();
    private CommunityAdapter communityAdapter;
    private CommunityCommentAdapter communityCommentAdapter;
    private ImageView community_publish;

    @Override
    public void initView() {
        View view = getView();
        recyclerView = view.findViewById(R.id.community_recycle);
        community_publish = view.findViewById(R.id.go_publish);
        comment_recycle=view.findViewById(R.id.comment_recycle);
    }

    @Override
    public void initData() {
        pre.CommunityData();

        //点击跳转到发表帖子页面
        community_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PublishActivity.class);
                startActivity(intent);
            }
        });

//        communityAdapter.setFindUserHead(new CommunityAdapter.FindUserHead() {
//            @Override
//            public void finduser(int index) {
//                Intent intent=new Intent(getActivity(), FindUserActivity.class);
//                intent.putExtra("userId",index);
//                startActivity(intent);
//            }
//        });

    }

    public void CommunityData(String json) {
        //解析
        CommuntiyBean communtiyBean = new Gson().fromJson(json, CommuntiyBean.class);
        list.addAll(communtiyBean.getResult());
//        list2.addAll(communtiyBean.getResult());
        communityAdapter = new CommunityAdapter(list);
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
