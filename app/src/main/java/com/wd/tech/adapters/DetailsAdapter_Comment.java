package com.wd.tech.adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.DetailsCommentBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.beans.JsonDetailsCommentsBean;

import java.util.List;

public class DetailsAdapter_Comment extends RecyclerView.Adapter<DetailsAdapter_Comment.ViewHolder> {
    private List<JsonDetailsCommentsBean.ResultBean> list;

    public DetailsAdapter_Comment(List<JsonDetailsCommentsBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.detailscomment_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.detailscomment_nickName.setText(list.get(position).getNickName());
        holder.detailscomment_content.setText(list.get(position).getContent());
        String head = list.get(position).getHeadPic();
        Uri uri = Uri.parse(head);
        holder.detailscomment_head.setImageURI(uri);
        String time = TypeConversionUtils.long2String(list.get(position).getCommentTime());
        holder.detailscomment_time.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView detailscomment_nickName, detailscomment_time, detailscomment_content;
        SimpleDraweeView detailscomment_head;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            detailscomment_content = itemView.findViewById(R.id.detailscomment_content);
            detailscomment_time = itemView.findViewById(R.id.detailscomment_time);
            detailscomment_nickName = itemView.findViewById(R.id.detailscomment_nickName);
            detailscomment_head = itemView.findViewById(R.id.detailscomment_head);

            if (onDetailsComment != null) {
                onDetailsComment.DetailsComment(list.get(getLayoutPosition()).getInfoId());
            }
        }
    }

    public interface OnDetailsComment {
        void DetailsComment(int index);
    }

    private OnDetailsComment onDetailsComment;

    public void setOnDetailsComment(OnDetailsComment onDetailsComment) {
        this.onDetailsComment = onDetailsComment;
    }
}
