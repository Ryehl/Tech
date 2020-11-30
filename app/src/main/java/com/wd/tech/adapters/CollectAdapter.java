package com.wd.tech.adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.CollectBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class CollectAdapter extends RecyclerView.Adapter<CollectAdapter.ViewHolder> {
    private List<CollectBean.ResultBean> list;

    public CollectAdapter(List<CollectBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.mycollect_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String i = list.get(position).getThumbnail();
        Uri uri = Uri.parse(i);
        holder.mycollect_img.setImageURI(uri);

        holder.mycollect_title.setText(list.get(position).getTitle());

        String time = TypeConversionUtils.long2StringAgo(list.get(position).getCreateTime());
        holder.mycollect_time.setText(time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mycollect_title, mycollect_time;
        SimpleDraweeView mycollect_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mycollect_img = itemView.findViewById(R.id.mycollect_img);
            mycollect_time = itemView.findViewById(R.id.mycollect_item);
            mycollect_title = itemView.findViewById(R.id.mycollect_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(go_details!=null){
                        go_details.details(list.get(getLayoutPosition()).getInfoId());
                    }
                }
            });

        }
    }

    public interface Go_Details{
        void details(int index);
    }
    private Go_Details go_details;

    public void setGo_details(Go_Details go_details) {
        this.go_details = go_details;
    }
}
