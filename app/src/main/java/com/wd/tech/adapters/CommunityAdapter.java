package com.wd.tech.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.MyApp;
import com.wd.tech.R;
import com.wd.tech.beans.CommuntiyBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    private List<CommuntiyBean.ResultBean> list;
    private List<String> list1 = new ArrayList<>();

    public CommunityAdapter(List<CommuntiyBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.community_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //赋值
        holder.community_nickName.setText(list.get(position).getNickName());
        String time = TypeConversionUtils.long2String(list.get(position).getPublishTime());
        holder.community_time.setText(time);//时间
        holder.community_signature.setText(list.get(position).getSignature());//签名
        holder.community_content.setText(list.get(position).getContent());//评论内容
        holder.community_comment_num.setText(list.get(position).getComment() + "");//评论数
        holder.community_praise_num.setText(list.get(position).getPraise() + "");//点赞数
        String head = list.get(position).getHeadPic();//头像
        Uri uri = Uri.parse(head);
        holder.community_head_img.setImageURI(uri);

//        String a = list.get(position).getFile();//图片
//        Uri uri1 = Uri.parse(a);
//        holder.community_img.setImageURI(uri1);

        //切割图片 并放进集合
        String[] split = list.get(position).getFile().split(",");
        list1.clear();
        for (int i = 0; i < split.length; i++) {
            list1.add(split[i]);
        }
        //设置网格图片
        CommunityImageAdapter communityImageAdapter = new CommunityImageAdapter(list1);
        holder.communityimg_recycle.setAdapter(communityImageAdapter);
        holder.communityimg_recycle.setLayoutManager(new GridLayoutManager(MyApp.context, 3));

        //设置评论
        CommunityCommentAdapter communityCommentAdapter=new CommunityCommentAdapter(list.get(position).getCommunityCommentVoList());
        holder.comment_recycle.setAdapter(communityCommentAdapter);
        holder.comment_recycle.setLayoutManager(new LinearLayoutManager(MyApp.context));
        //判断评论长度
        if(list.get(position).getComment()==0){
            holder.comment_but.setText("快来评论呀～");

        }else if(list.get(position).getComment()>0&&list.get(position).getComment()<4){
            holder.comment_but.setText("没有更多评论了～");
            //通过接口回调  点击跳转到评论页面
            holder.comment_but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(commentList!=null){
                        commentList.comment(list.get(position).getId(),list.get(position).getHeadPic(),list.get(position).getNickName(),list.get(position).getComment());

                        communityCommentAdapter.setOnComment(new CommunityCommentAdapter.OnComment() {
                            @Override
                            public void comment(String nickName, String content) {
//                                Intent intent=new Intent()
                            }
                        });
                    }
                }
            });
        }else{
            holder.comment_but.setText("点击查看更多评论");
            holder.comment_but.setTextColor(Color.GREEN);
            //通过接口回调  点击跳转到评论页面
            holder.comment_but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(commentList!=null){
                        commentList.comment(list.get(position).getId(),list.get(position).getHeadPic(),list.get(position).getNickName(),list.get(position).getComment());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView community_nickName, community_time, community_praise_num, community_comment_num, community_content, community_signature;
        SimpleDraweeView community_head_img;
        TextView comment_nickName, comment_content,comment_but;
        RecyclerView communityimg_recycle,comment_recycle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            community_signature = itemView.findViewById(R.id.community_signature);
            community_praise_num = itemView.findViewById(R.id.community_praise_num);
            community_content = itemView.findViewById(R.id.community_content);
            community_head_img = itemView.findViewById(R.id.community_head_img);
            community_nickName = itemView.findViewById(R.id.community_nickName);
            community_time = itemView.findViewById(R.id.community_time);
            community_comment_num = itemView.findViewById(R.id.community_comment_num);
            communityimg_recycle = itemView.findViewById(R.id.communityimg_recycle);
            comment_recycle=itemView.findViewById(R.id.comment_recycle);
            comment_but=itemView.findViewById(R.id.comment_but);

            //评论的
            comment_nickName = itemView.findViewById(R.id.comment_nickName);
            comment_content = itemView.findViewById(R.id.comment_content);

            //点击头像跳转到用户中心页面
            community_head_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (findUserHead != null) {
                        findUserHead.finduser(list.get(getLayoutPosition()).getUserId(), list.get(getLayoutPosition()).getHeadPic(), list.get(getLayoutPosition()).getSignature(), list.get(getLayoutPosition()).getNickName());
                    }
                }
            });


        }

    }

    //接口回调 跳转到发帖子的用户的个人中心
    public interface FindUserHead {
        void finduser(int index, String head, String signature, String nickName);
    }

    public FindUserHead findUserHead;

    public void setFindUserHead(FindUserHead findUserHead) {
        this.findUserHead = findUserHead;
    }

    //接口回调  跳转帖子的评论页
    public interface CommentList{
        void comment(int commentId,String head,String nickName,int comment);
    }
    private CommentList commentList;

    public void setCommentList(CommentList commentList) {
        this.commentList = commentList;
    }
}
