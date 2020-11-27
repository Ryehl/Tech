package com.wd.tech.adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.CommunityCommentListBean;

import java.util.List;

public class CommunityCommentListTAdapter extends RecyclerView.Adapter<CommunityCommentListTAdapter.ViewHolder> {
    private List<CommunityCommentListBean.ResultBean> list;

    public CommunityCommentListTAdapter(List<CommunityCommentListBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.community_commenttlist_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //赋值
        holder.commentt_nickName.setText(list.get(position).getNickName());
        holder.commentt_content.setText(list.get(position).getContent());
        //时间
        String time= TypeConversionUtils.long2String(list.get(position).getCommentTime());
        holder.comentt_time.setText(time);

        //头像
        String img=list.get(position).getHeadPic();
        Uri uri=Uri.parse(img);
        holder.commentt_head.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView commentt_nickName,commentt_content,comentt_time;
        SimpleDraweeView commentt_head;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            commentt_nickName=itemView.findViewById(R.id.commentt_nickName);
            commentt_content=itemView.findViewById(R.id.commentt_content);
            comentt_time=itemView.findViewById(R.id.commentt_time);
            commentt_head=itemView.findViewById(R.id.commentt_head);
        }
    }
}
