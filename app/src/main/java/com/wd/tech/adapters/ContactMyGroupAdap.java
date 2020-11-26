package com.wd.tech.adapters;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.activities.GroupInfoActivity;
import com.wd.tech.beans.JsonFriendInfoBean;

import java.util.List;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:好友信息页面我的群组适配器</p>
 *
 * @author Xaoyv
 * date 11/25/2020 11:51 AM
 */
public class ContactMyGroupAdap extends RecyclerView.Adapter<ContactMyGroupAdap.Holder> {
    private List<JsonFriendInfoBean.ResultBean.MyGroupListBean> list;

    public ContactMyGroupAdap(List<JsonFriendInfoBean.ResultBean.MyGroupListBean> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        //setText
        holder.tv_groupName.setText(list.get(position).getGroupName());
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(holder.sdv_groupHd.getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        holder.sdv_groupHd.setHierarchy(hierarchy);
        holder.sdv_groupHd.setImageURI(list.get(position).getGroupImage());
        holder.sdv_groupHd.setOnClickListener(v -> {
            //跳转到群聊详情页
            Intent in = new Intent(holder.itemView.getContext(), GroupInfoActivity.class);
            in.putExtra("groupId", list.get(position).getGroupId());
            holder.itemView.getContext().startActivity(in);
        });
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.item_contact_mygroup_recy, null));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv_groupHd;
        TextView tv_groupName;

        Holder(@NonNull View itemView) {
            super(itemView);
            sdv_groupHd = itemView.findViewById(R.id.item_contact_mygroup_sdv_groupHd);
            tv_groupName = itemView.findViewById(R.id.item_contact_mygroup_tv_groupName);
        }
    }
}
