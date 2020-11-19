package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.DetailsBean;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    private List<DetailsBean.ResultBean> list;

    public DetailsAdapter(List<DetailsBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.details_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.details_title.setText(list.get(position).getTitle());
        holder.details_author.setText(list.get(position).getSource());
        String time= TypeConversionUtils.long2String(list.get(position).getReleaseTime());
        holder.details_time.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView details_title,details_time,details_author;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            details_author=itemView.findViewById(R.id.details_author);
            details_time=itemView.findViewById(R.id.details_time);
            details_title=itemView.findViewById(R.id.details_title);
        }
    }
}
