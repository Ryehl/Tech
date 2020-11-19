package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.beans.IntegralRecordBean;

import java.util.List;

public class IntegralRecordAdapter extends RecyclerView.Adapter<IntegralRecordAdapter.ViewHolder> {
    private List<IntegralRecordBean.ResultBean> list;

    public IntegralRecordAdapter(List<IntegralRecordBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.myintegralrecord_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
  //      holder.myintegralrecord_title.setText(list.get(position).);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myintegralrecord_title,myintegralrecord_time,myintegralrecord_num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myintegralrecord_num=itemView.findViewById(R.id.myintegralrecord_num);
            myintegralrecord_time=itemView.findViewById(R.id.myintegralrecord_time);
            myintegralrecord_title=itemView.findViewById(R.id.myintegralrecord_title);
        }
    }
}
