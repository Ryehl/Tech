package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.MyApp;
import com.wd.tech.R;
import com.wd.tech.Urls;
import com.wd.tech.beans.JsonFriendReqNotBean;

import java.util.HashMap;
import java.util.List;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:好友请求通知适配器</p>
 *
 * @author Xaoyv
 * date 12/8/2020 8:16 AM
 */
public class FriendNoticeAdap extends RecyclerView.Adapter {
    private static final int TREATED = 0;
    private static final int UNTREATED = 1;
    private List<JsonFriendReqNotBean.ResultBean> list;

    public FriendNoticeAdap(List<JsonFriendReqNotBean.ResultBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TREATED)
            return new TreHolder(View.inflate(parent.getContext(), R.layout.item_friend_reqnot_treated, null));
        else
            return new UntreHolder(View.inflate(parent.getContext(), R.layout.item_friend_reqnot_untreated, null));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TREATED) {
            //已处理
            TreHolder treHolder = (TreHolder) holder;
            treHolder.sdv_hd.setHierarchy(getHie(holder.itemView));
            treHolder.sdv_hd.setImageURI(list.get(position).getFromHeadPic());
            treHolder.tv_name.setText(list.get(position).getFromNickName());
            treHolder.tv_remark.setText(list.get(position).getRemark());
            treHolder.tv_result.setText(list.get(position).getStatus() == 2 ? "已同意" : "已拒绝");
        } else {
            //未处理
            UntreHolder untreHolder = (UntreHolder) holder;
            untreHolder.sdv_hd.setHierarchy(getHie(holder.itemView));
            untreHolder.sdv_hd.setImageURI(list.get(position).getFromHeadPic());
            untreHolder.tv_name.setText(list.get(position).getFromNickName());
            untreHolder.tv_remark.setText(list.get(position).getRemark());
            untreHolder.tv_agree.setOnClickListener(v -> {
                HashMap<String, Object> map = new HashMap<>();
                map.put("noticeId", list.get(position).getNoticeId());
                map.put("flag", 2);
                argOrRef(map);
            });
            untreHolder.tv_refuse.setOnClickListener(v -> {
                HashMap<String, Object> map = new HashMap<>();
                map.put("noticeId", list.get(position).getNoticeId());
                map.put("flag", 3);
                argOrRef(map);
            });
        }
    }

    private void argOrRef(HashMap<String, Object> map) {
        NetUtils.getNetUtils().putInfo(Urls.Agree_Friendreq, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                Toast.makeText(MyApp.context, "请求成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void error() {
            }
        });
    }

    private GenericDraweeHierarchy hie;

    private GenericDraweeHierarchy getHie(View view) {
        return hie == null ? new GenericDraweeHierarchyBuilder(view.getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build() : hie;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getStatus() == 1 ? UNTREATED : TREATED;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TreHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv_hd;
        TextView tv_name;
        TextView tv_remark;
        TextView tv_result;

        TreHolder(@NonNull View itemView) {
            super(itemView);
            sdv_hd = itemView.findViewById(R.id.item_friendreqnot_sdv_hd);
            tv_name = itemView.findViewById(R.id.item_friendreqnot_tv_name);
            tv_remark = itemView.findViewById(R.id.item_friendreqnot_tv_remark);
            tv_result = itemView.findViewById(R.id.item_friendreqnot_tv_result);
        }
    }

    class UntreHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv_hd;
        TextView tv_name;
        TextView tv_remark;
        TextView tv_agree;
        TextView tv_refuse;

        UntreHolder(@NonNull View itemView) {
            super(itemView);
            sdv_hd = itemView.findViewById(R.id.item_friendreqnot_sdv_hd);
            tv_name = itemView.findViewById(R.id.item_friendreqnot_tv_name);
            tv_remark = itemView.findViewById(R.id.item_friendreqnot_tv_remark);
            tv_agree = itemView.findViewById(R.id.item_friendreqnot_tv_agree);
            tv_refuse = itemView.findViewById(R.id.item_friendreqnot_tv_refuse);
        }
    }
}
