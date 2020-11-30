package com.wd.tech.activities;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.adapters.CommunityCommentAdapter;
import com.wd.tech.adapters.CommunityCommentListTAdapter;
import com.wd.tech.beans.AddCommunityCommentBean;
import com.wd.tech.beans.CommunityCommentListBean;
import com.wd.tech.presenters.CommunityCommentListPresenter;

import java.util.ArrayList;
import java.util.List;

public class CommunityCommentListActivity extends BaseActivity<CommunityCommentListPresenter> {

    private TextView commentuser_nickName,commentlist_num;
    private SimpleDraweeView commentuser_head;
    private CommunityCommentListTAdapter commentListTAdapter;
    private List<CommunityCommentListBean.ResultBean> list=new ArrayList<>();
    private XRecyclerView xRecyclerView;
    private ImageView back;
    private Button publish_comment;
    private EditText publish_content;
    private int commentId;
    private int page=1;
    @Override
    public void initView() {
        commentuser_nickName=findViewById(R.id.commentuser_nickName);
        commentuser_head=findViewById(R.id.commentuser_head);
        commentlist_num=findViewById(R.id.commentlist_num);
        xRecyclerView=findViewById(R.id.commentlist_recycle);
        back=findViewById(R.id.back);
        publish_comment=findViewById(R.id.publish_comment);
        publish_content=findViewById(R.id.publish_content);
    }

    @Override
    public void initData() {
        //接收传过来的值
        Intent intent=getIntent();
        commentId=intent.getIntExtra("commentId",0);
        String head=intent.getStringExtra("head");
        String nickName=intent.getStringExtra("nickName");
        int comment=intent.getIntExtra("comment",0);
        //赋值
        Uri uri=Uri.parse(head);
        commentuser_head.setImageURI(uri);
        commentuser_nickName.setText(nickName);
        commentlist_num.setText(comment+"");

        //评论的方法
        pre.getCommentListData(commentId);

        //返回上一个页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    //发表评论
    public void AddCommunityCommentData(String json){
        //解析
        AddCommunityCommentBean addCommunityCommentBean=new Gson().fromJson(json,AddCommunityCommentBean.class);
        Toast.makeText(this, addCommunityCommentBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    //展示评论
    public void CommentListData(String json){
        //解析
        Log.i("tag",json);
        list.clear();
        CommunityCommentListBean communityCommentListBean=new Gson().fromJson(json,CommunityCommentListBean.class);
        list=communityCommentListBean.getResult();
        commentListTAdapter=new CommunityCommentListTAdapter(list);
        xRecyclerView.setAdapter(commentListTAdapter);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(CommunityCommentListActivity.this));

        //点击发表评论
        publish_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拿到输入框的值(评论内容)
                String content=publish_content.getText().toString();
                pre.getAddCommunityCommentData(content,commentId);


            }
        });

        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
             //   pre.getCommentListData2(commentId,page);
                commentListTAdapter.notifyDataSetChanged();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
            //]..    pre.getCommentListData2(commentId,page);
                commentListTAdapter.notifyDataSetChanged();
                xRecyclerView.loadMoreComplete();
            }
        });
    }
    public void CommentListData2(String json){
        CommunityCommentListBean communityCommentListBean=new Gson().fromJson(json,CommunityCommentListBean.class);
        list.addAll(communityCommentListBean.getResult());

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
