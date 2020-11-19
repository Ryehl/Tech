package com.wd.tech.adapters;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.beans.AttentionBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class AttentionAdapter extends RecyclerView.Adapter<AttentionAdapter.ViewHolder> {
    private List<AttentionBean.ResultBean> list;

    public AttentionAdapter(List<AttentionBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.myattention_item,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myattention_signture.setText(list.get(position).getSignature());
        holder.myattention_nickName.setText(list.get(position).getNickName());

        String s=list.get(position).getHeadPic();
        Uri uri=Uri.parse(s);
        holder.myattention_img.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myattention_nickName,myattention_signture;
        SimpleDraweeView myattention_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myattention_img=itemView.findViewById(R.id.myattention_head);
            myattention_nickName=itemView.findViewById(R.id.myattention_nickName);
            myattention_signture=itemView.findViewById(R.id.myattention_signature);
        }
    }
}
