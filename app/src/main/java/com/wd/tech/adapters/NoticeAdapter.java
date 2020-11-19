package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.NoticeBean;

import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> {
    private List<NoticeBean.ResultBean> list;

    public NoticeAdapter(List<NoticeBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.mynotice_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mynotice_content.setText(list.get(position).getContent());
        String time= TypeConversionUtils.long2String(list.get(position).getCreateTime());
        holder.mynotice_time.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mynotice_time,mynotice_content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mynotice_time=itemView.findViewById(R.id.mynotice_time);
            mynotice_content=itemView.findViewById(R.id.mynotice_content);

        }
    }
}
