package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.beans.AddCommunityCommentBean;

import java.util.List;

public class AddCommunityCommentAdapter extends RecyclerView.Adapter<AddCommunityCommentAdapter.ViewHolder> {
    private List<AddCommunityCommentBean> list;

    public AddCommunityCommentAdapter(List<AddCommunityCommentBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
