package com.wd.tech.utils;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.gson.Gson;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.ChatFriendActivity;
import com.wd.tech.activities.ChatGroupActivity;
import com.wd.tech.activities.JsonFriendInfoByJusernameBean;
import com.wd.tech.broadcast.MyMessageReceiver;

import java.util.HashMap;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

public class GlobalEventListener {
    private Context appContext;

    public GlobalEventListener(Context context) {
        appContext = context;
        JMessageClient.registerEventReceiver(this);
//        IntentFilter intentFilter = IntentFilter.create("", "");
//        LocalBroadcastManager.getInstance(context).registerReceiver(new MyMessageReceiver(), intentFilter);
    }

    /**
     * 通知栏点击
     *
     * @param event 事件
     */
    public void onEvent(NotificationClickEvent event) {
        //点击进行跳转到聊天页面
        Message msg = event.getMessage();
        UserInfo fromUser = msg.getFromUser();
        if (msg.getTargetType() == ConversationType.group) {
            GroupInfo groupInfo = (GroupInfo) msg.getTargetInfo();
            Intent intent = new Intent(appContext, ChatGroupActivity.class);
            intent.putExtra("groupId", groupInfo.getGroupID());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            appContext.startActivity(intent);
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("userNames", fromUser.getUserName());
            NetUtils.getNetUtils().getInfo(Urls.FriendInfo_JUserName, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    Intent intent = new Intent(appContext, ChatFriendActivity.class);
                    intent.putExtra("userName", fromUser.getUserName());
                    JsonFriendInfoByJusernameBean friends = new Gson().fromJson(json, JsonFriendInfoByJusernameBean.class);
                    intent.putExtra("friendUid", friends.getResult().get(0).getUserId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    appContext.startActivity(intent);
                }

                @Override
                public void error() {
                }
            });
        }
    }

    public void onEvent(MessageEvent event) {
        //TODO 发送广播
    }

    /**
     * 好友相关通知
     * TODO 发送广播
     *
     * @param event 通知
     */
    public void onEvent(ContactNotifyEvent event) {
        String reason = event.getReason();
        String fromUsername = event.getFromUsername();
        //String appkey = event.getfromUserAppKey();

        switch (event.getType()) {
            case invite_received://收到好友邀请
                //...
                break;
            case invite_accepted://对方接收了你的好友邀请
                //...
                break;
            case invite_declined://对方拒绝了你的好友邀请
                //...
                break;
            case contact_deleted://对方将你从好友中删除
                //...
                break;
            default:
                break;
        }
    }
}
