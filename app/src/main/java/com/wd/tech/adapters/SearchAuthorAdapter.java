package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.SearchAuthorBean;

import java.util.List;

public class SearchAuthorAdapter extends RecyclerView.Adapter<SearchAuthorAdapter.ViewHolder> {
    private List<SearchAuthorBean.ResultBean> list;

    public SearchAuthorAdapter(List<SearchAuthorBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.searchauthor_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.article_author.setText(list.get(position).getSource());
        holder.article_title.setText(list.get(position).getTitle());
        String time= TypeConversionUtils.long2String(list.get(position).getReleaseTime());
        holder.article_time.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView article_title,article_time,article_author;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            article_title=itemView.findViewById(R.id.article_title);
            article_time=itemView.findViewById(R.id.article_time);
            article_author=itemView.findViewById(R.id.article_author);
        }
    }
}
