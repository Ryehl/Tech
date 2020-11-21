package com.wd.tech.adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.CardBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private List<CardBean.ResultBean> list;

    public CardAdapter(List<CardBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.mycard_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mycard_praise_num.setText(list.get(position).getPraise() + "");
        holder.mycard_comment_num.setText(list.get(position).getComment() + "");
        String s = list.get(position).getFile();
        Uri uri = Uri.parse(s);
        holder.mycard_img.setImageURI(uri);
        String time = TypeConversionUtils.long2String(list.get(position).getPublishTime());
        holder.mycard_time.setText(time);
        holder.mycard_content.setText(list.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mycard_content, mycard_time, mycard_delete, mycard_comment_num, mycard_praise_num;
        SimpleDraweeView mycard_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mycard_comment_num = itemView.findViewById(R.id.mycard_comment_num);
            mycard_content = itemView.findViewById(R.id.mycard_content);
            mycard_delete = itemView.findViewById(R.id.mycard_delete);
            mycard_time = itemView.findViewById(R.id.mycard_time);
            mycard_praise_num = itemView.findViewById(R.id.mycard_praise_num);
            mycard_img = itemView.findViewById(R.id.mycard_img);

        }
    }
}
