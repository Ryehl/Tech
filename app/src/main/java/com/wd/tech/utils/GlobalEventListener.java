package com.wd.tech.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.ChatFriendActivity;
import com.wd.tech.activities.ChatGroupActivity;
import com.wd.tech.activities.JsonFriendInfoByJusernameBean;

import java.util.HashMap;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * 通知栏点击监听
 */
public class GlobalEventListener {
    private Context appContext;

    public GlobalEventListener(Context context) {
        appContext = context;
        JMessageClient.registerEventReceiver(this);
    }

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
    }
}
