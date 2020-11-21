package com.wd.tech.adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.beans.InterestBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.ViewHolder> {
    private List<InterestBean.ResultBean> list;

    public InterestAdapter(List<InterestBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.interest_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s = list.get(position).getPic();
        Uri uri = Uri.parse(s);
        holder.interest_img.setImageURI(uri);

        holder.interest_title.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView interest_img;
        TextView interest_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            interest_img = itemView.findViewById(R.id.interest_img);
            interest_title = itemView.findViewById(R.id.interest_title);
        }
    }
}
