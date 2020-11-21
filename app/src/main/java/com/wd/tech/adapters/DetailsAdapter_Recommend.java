package com.wd.tech.adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.beans.DetailsBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class DetailsAdapter_Recommend extends RecyclerView.Adapter<DetailsAdapter_Recommend.ViewHolder> {
    private List<DetailsBean.ResultBean.InformationListBean> list;

    public DetailsAdapter_Recommend(List<DetailsBean.ResultBean.InformationListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.details_item_two, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.details_recommend_title.setText(list.get(position).getTitle());
        String img = list.get(position).getThumbnail();
        Uri uri = Uri.parse(img);
        holder.details_recommend_img.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView details_recommend_title;
        SimpleDraweeView details_recommend_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            details_recommend_img = itemView.findViewById(R.id.details_recommend_img);
            details_recommend_title = itemView.findViewById(R.id.details_recommend_tile);
        }
    }
}
