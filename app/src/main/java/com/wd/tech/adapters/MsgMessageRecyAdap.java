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
import com.google.gson.Gson;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.Urls;
import com.wd.tech.beans.JsonFriendInfoByJusernameBean;
import com.wd.tech.utils.JMsgUtils;

import java.util.HashMap;
import java.util.List;

import cn.jpush.im.android.api.model.Conversation;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:消息会话列表适配器</p>
 *
 * @author Xaoyv
 * date 11/26/2020 2:16 AM
 */
public class MsgMessageRecyAdap extends RecyclerView.Adapter<MsgMessageRecyAdap.Holder> {

    private List<Conversation> list;

    public MsgMessageRecyAdap(List<Conversation> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(View.inflate(parent.getContext(), R.layout.item_msg_session, null));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(holder.itemView.getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        holder.sdv_hd.setHierarchy(hierarchy);
        Conversation conversation = list.get(position);
        //holder.img_hd.setImageURI();
        setImage(holder.sdv_hd, conversation.getTitle());
        //联系人姓名/群聊名
        holder.tv_name.setText(conversation.getTitle());
        //最后一条消息的时间
        holder.tv_date.setText(TypeConversionUtils.long2StringAgo(conversation.getLastMsgDate()));
        //未读消息数量
        int unReadMsgCnt = conversation.getUnReadMsgCnt();
        if (unReadMsgCnt > 0) {
            holder.tv_noReadCount.setText(String.valueOf(unReadMsgCnt));
            holder.tv_noReadCount.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(v -> {
        });
        if (conversation.getLatestMessage() == null)
            return;
        //最后一条消息内容
        holder.tv_lastMsg.setText(JMsgUtils.getjMsgUtils().getMessageContent(conversation.getLatestMessage()));
    }

    private void setImage(SimpleDraweeView sdv, String jName) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userNames", jName);
        NetUtils.getNetUtils().getInfo(Urls.FriendInfo_JUserName, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                JsonFriendInfoByJusernameBean bean = new Gson().fromJson(json, JsonFriendInfoByJusernameBean.class);
                try {
                    String headPic = bean.getResult().get(0)
                            .getHeadPic();
                    sdv.setImageURI(headPic);
                } catch (IndexOutOfBoundsException ignored) {
                }
            }

            @Override
            public void error() {
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv_hd;
        TextView tv_name;
        TextView tv_lastMsg;
        TextView tv_date;
        TextView tv_noReadCount;

        Holder(@NonNull View itemView) {
            super(itemView);
            sdv_hd = itemView.findViewById(R.id.item_msg_sdv_hd);
            tv_name = itemView.findViewById(R.id.item_msg_tv_name);
            tv_lastMsg = itemView.findViewById(R.id.item_msg_tv_lastMsg);
            tv_date = itemView.findViewById(R.id.item_msg_tv_date);
            tv_noReadCount = itemView.findViewById(R.id.item_msg_tv_noReadCount);
        }
    }
}
