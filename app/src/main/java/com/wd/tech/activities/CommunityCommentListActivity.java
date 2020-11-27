package com.wd.tech.activities;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.adapters.CommunityCommentAdapter;
import com.wd.tech.adapters.CommunityCommentListTAdapter;
import com.wd.tech.beans.CommunityCommentListBean;
import com.wd.tech.presenters.CommunityCommentListPresenter;

import java.util.ArrayList;
import java.util.List;

public class CommunityCommentListActivity extends BaseActivity<CommunityCommentListPresenter> {

    private TextView commentuser_nickName,commentlist_nickName,commentlist_content,commentlist_num;
    private SimpleDraweeView commentuser_head,commentlist_head;
    private CommunityCommentListTAdapter commentListTAdapter;
    private List<CommunityCommentListBean.ResultBean> list=new ArrayList<>();
    private XRecyclerView xRecyclerView;
    private ImageView back;
    @Override
    public void initView() {
        commentuser_nickName=findViewById(R.id.commentuser_nickName);
        commentuser_head=findViewById(R.id.commentuser_head);
        commentlist_num=findViewById(R.id.commentlist_num);
        xRecyclerView=findViewById(R.id.commentlist_recycle);
        back=findViewById(R.id.back);
    }

    @Override
    public void initData() {
        //接收传过来的值
        Intent intent=getIntent();
        int commentId=intent.getIntExtra("commentId",0);
        String head=intent.getStringExtra("head");
        String nickName=intent.getStringExtra("nickName");
        int comment=intent.getIntExtra("comment",0);
        //赋值
        Uri uri=Uri.parse(head);
        commentuser_head.setImageURI(uri);
        commentuser_nickName.setText(nickName);
        commentlist_num.setText(comment+"");

        pre.getCommentListData(commentId);

        //返回上一个页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void CommentListData(String json){
        //解析
        Log.i("tag",json);
        CommunityCommentListBean communityCommentListBean=new Gson().fromJson(json,CommunityCommentListBean.class);
        list.addAll(communityCommentListBean.getResult());
        commentListTAdapter=new CommunityCommentListTAdapter(list);
        xRecyclerView.setAdapter(commentListTAdapter);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(CommunityCommentListActivity.this));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_community_commentlist;
    }

    @Override
    public CommunityCommentListPresenter initPresenter() {
        return new CommunityCommentListPresenter();
    }
}
