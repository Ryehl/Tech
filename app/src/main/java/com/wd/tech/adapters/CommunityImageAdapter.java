package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;

import java.util.List;

public class CommunityImageAdapter extends RecyclerView.Adapter<CommunityImageAdapter.ViewHolder> {
    private List<String> list;

    public CommunityImageAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.communityimage_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //String img=list.get(position)
        holder.community_img.setImageURI(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView community_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            community_img=itemView.findViewById(R.id.community_img);
        }
    }
}
