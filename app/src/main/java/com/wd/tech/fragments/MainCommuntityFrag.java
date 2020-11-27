package com.wd.tech.fragments;


import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.mylibrary.base.BaseFragment;
import com.wd.tech.MyApp;
import com.wd.tech.R;
import com.wd.tech.activities.CommunityCommentListActivity;
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

    XRecyclerView recyclerView;
    private List<CommuntiyBean.ResultBean> list = new ArrayList<>();
    private List<CommuntiyBean.ResultBean.CommunityCommentVoListBean> list2=new ArrayList<>();
    private CommunityAdapter communityAdapter;
    private SimpleDraweeView community_publish;
    private int page=1,count=5;
    @Override
    public void initView() {
        View view = getView();
        recyclerView = view.findViewById(R.id.community_recycle);
        community_publish = view.findViewById(R.id.go_publish);

    }

    @Override
    public void initData() {
        pre.CommunityData(page,count);

        //点击跳转到发表帖子页面
        community_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PublishActivity.class);
                startActivity(intent);
            }
        });



    }

    public void CommunityData(String json) {
        //解析
        CommuntiyBean communtiyBean = new Gson().fromJson(json, CommuntiyBean.class);
        list=communtiyBean.getResult();
//        list2.addAll(communtiyBean.getResult());
        communityAdapter = new CommunityAdapter(list);
        recyclerView.setAdapter(communityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyApp.context));

        /**
         * 上拉加载  下拉刷新
         */
        //开启下拉刷新  下拉加载
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                pre.CommunityData(page,count);
                communityAdapter.notifyDataSetChanged();
                recyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                pre.CommunityData2(page,count);
                recyclerView.loadMoreComplete();
            }
        });
        //点击头像跳转到个人中心页面
        communityAdapter.setFindUserHead(new CommunityAdapter.FindUserHead() {
            @Override
            public void finduser(int index, String head, String signature,String nickName) {
                Intent intent=new Intent(getActivity(), FindUserActivity.class);
                intent.putExtra("userId",index);
                intent.putExtra("head",head);
                intent.putExtra("signature",signature);
                intent.putExtra("nickName",nickName);
                startActivity(intent);
            }
        });

        //点击跳转到评论页面
        communityAdapter.setCommentList(new CommunityAdapter.CommentList() {
            @Override
            public void comment(int commentId, String head, String nickName, int comment) {
                Intent intent=new Intent(getActivity(), CommunityCommentListActivity.class);
                intent.putExtra("commentId",commentId);
                intent.putExtra("head",head);
                intent.putExtra("nickName",nickName);
                intent.putExtra("comment",comment);
                startActivity(intent);
            }
        });
    }
    public void CommunityData2(String json){
        CommuntiyBean communtiyBean = new Gson().fromJson(json, CommuntiyBean.class);
        list.addAll(communtiyBean.getResult());
        communityAdapter.notifyDataSetChanged();
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
