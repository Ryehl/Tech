package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.beans.IntegralBean;

import java.util.List;

public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.ViewHolder> {
    private List<IntegralBean.ResultBean> list;

    public IntegralAdapter(List<IntegralBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.myintegral_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.integral_num.setText(list.get(position).getAmount()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView integral_num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            integral_num=itemView.findViewById(R.id.integral_num);
        }
    }
}
