package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.beans.CommuntiyBean;

import java.util.List;

public class CommunityCommentAdapter extends RecyclerView.Adapter<CommunityCommentAdapter.ViewHolder> {
    private List<CommuntiyBean.ResultBean.CommunityCommentVoListBean> list;

    public CommunityCommentAdapter(List<CommuntiyBean.ResultBean.CommunityCommentVoListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.communitycomment_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.comment_content.setText(list.get(position).getContent());
        holder.comment_nickName.setText(list.get(position).getNickName()+":");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView comment_nickName,comment_content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comment_nickName=itemView.findViewById(R.id.comment_nickName);
            comment_content=itemView.findViewById(R.id.comment_content);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(onComment!=null){
//                        onComment.comment(list.get(getLayoutPosition()).getNickName(),list.get(getLayoutPosition()).getContent());
//                    }
//                }
//            });
        }
    }

    public interface OnComment{
        void comment(String nickName,String content);
    }
    private OnComment onComment;

    public void setOnComment(OnComment onComment) {
        this.onComment = onComment;
    }
}
