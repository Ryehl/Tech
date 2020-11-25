package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.beans.FindUserBean;

import java.util.List;

public class FindUserAdapter extends RecyclerView.Adapter<FindUserAdapter.ViewHolder> {
//    private List<FindUserBean.ResultBean.CommunityUserVoBean> list;
//
//    public FindUserAdapter(List<FindUserBean.ResultBean.CommunityUserVoBean> list) {
//        this.list = list;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.find_user_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView finduser_content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            finduser_content=itemView.findViewById(R.id.find_user_content);
        }
    }
}
