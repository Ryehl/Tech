package com.wd.tech.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.tencent.mmkv.MMKV;
import com.wd.mylibrary.bean.ConstantMMkv;
import com.wd.tech.R;

import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.model.Message;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:聊天适配器</p>
 *
 * @author Xaoyv
 * date 11/27/2020 3:03 PM
 */
public class ChatViewRecyAdap extends RecyclerView.Adapter {

    private List<Message> list;
    private static final int send = 0;
    private static final int receive = 1;
    private GenericDraweeHierarchy hierarchy;
    private Map<String, String> map;
    private MMKV kv;

    public ChatViewRecyAdap(List<Message> list, Map<String, String> map) {
        this.list = list;
        this.map = map;
        kv = MMKV.defaultMMKV();
    }

    class TheirMsgHolder extends RecyclerView.ViewHolder {
        ImageView img_hd;
        TextView tv_name;
        RelativeLayout rel;

        TheirMsgHolder(@NonNull View itemView) {
            super(itemView);
            img_hd = itemView.findViewById(R.id.item_chat_their_img_hd);
            tv_name = itemView.findViewById(R.id.item_chat_their_tv_name);
            rel = itemView.findViewById(R.id.item_chat_their_rel_msg);
        }
    }

    class MyMsgHolder extends RecyclerView.ViewHolder {
        ImageView img_hd;
        TextView tv_name;
        RelativeLayout rel;

        MyMsgHolder(@NonNull View itemView) {
            super(itemView);
            img_hd = itemView.findViewById(R.id.item_chat_me_img_hd);
            tv_name = itemView.findViewById(R.id.item_chat_me_tv_name);
            rel = itemView.findViewById(R.id.item_chat_me_rel_msg);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case send:
                return new MyMsgHolder(View.inflate(parent.getContext(), R.layout.item_chat_me, null));
            case receive:
                return new TheirMsgHolder(View.inflate(parent.getContext(), R.layout.item_chat_their, null));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //多布局
        switch (getItemViewType(position)) {
            case send:
                //我发送的消息
                MyMsgHolder myMsgHolder = (MyMsgHolder) holder;
                Glide.with(myMsgHolder.itemView.getContext())
                        .load(map.get(kv.decodeString(ConstantMMkv.Key_UserName)))
                        .into(myMsgHolder.img_hd);
                myMsgHolder.tv_name.setText(list.get(position).getFromUser().getNickname());
                //TODO 展示消息内容
                MessageContent content = list.get(position).getContent();
                if (content.getContentType() == ContentType.text) {
                    TextContent textContent = (TextContent) content;
                    TextView textView = new TextView(myMsgHolder.rel.getContext());
                    textView.setText(textContent.getText());
                    myMsgHolder.rel.removeAllViews();
                    myMsgHolder.rel.addView(textView);
                }
                break;
            case receive:
                TheirMsgHolder theirMsgHolder = (TheirMsgHolder) holder;
                Glide.with(theirMsgHolder.itemView.getContext())
                        .load( map.get(list.get(position).getFromUser().getUserName()))
                        .into(theirMsgHolder.img_hd);
                theirMsgHolder.tv_name.setText(list.get(position).getFromUser().getNickname());
                //TODO 展示消息内容
                MessageContent their_content = list.get(position).getContent();
                if (their_content.getContentType() == ContentType.text) {
                    TextContent textContent = (TextContent) their_content;
                    TextView textView = new TextView(theirMsgHolder.rel.getContext());
                    textView.setText(textContent.getText());
                    theirMsgHolder.rel.removeAllViews();
                    theirMsgHolder.rel.addView(textView);
                }
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).getDirect()) {
            case send:
                return send;
            case receive:
                return receive;
            default:
                return -1;
        }
    }

    public GenericDraweeHierarchy getHierarchy(View view) {
        if (hierarchy == null)
            hierarchy = new GenericDraweeHierarchyBuilder(view.getResources())
                    .setRoundingParams(RoundingParams.asCircle())
                    .build();
        return hierarchy;
    }
}
