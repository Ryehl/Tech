package com.bw.tech.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.tech.R;
import com.bw.tech.beans.IntegralBean;

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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
