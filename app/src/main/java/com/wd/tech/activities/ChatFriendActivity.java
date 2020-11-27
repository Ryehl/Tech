package com.wd.tech.activities;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.beans.JsonChatHisBean;
import com.wd.tech.beans.JsonFriendInfoBean;
import com.wd.tech.presenters.ActChatFriendPresenter;
import com.wd.tech.utils.JIMUtils;

import java.util.List;
import java.util.Locale;

import cn.jmessage.biz.httptask.task.GetEventNotificationTaskMng;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

public class ChatFriendActivity extends BaseActivity<ActChatFriendPresenter> {

    private int friendUid;
    private String userName;
    private String TAG = this.getClass().getName();

    private TextView tv_friendName;
    private RecyclerView recy_showChat;
    private EditText et_input;
    private Button btn_send;
    private ImageView img_vol;
    private ImageView img_img;
    private ImageView img_camera;
    private ImageView img_position;
    private JsonFriendInfoBean friendInfoBean;

    @Override
    public void initView() {
        tv_friendName = findViewById(R.id.chat_friend_tv_name);
        recy_showChat = findViewById(R.id.chat_friend_recy_chat);

        et_input = findViewById(R.id.include_chat_et_input);
        btn_send = findViewById(R.id.include_chat_btn_send);
        img_vol = findViewById(R.id.include_chat_img_vol);
        img_img = findViewById(R.id.include_chat_img_photo);
        img_camera = findViewById(R.id.include_chat_img_camera);
        img_position = findViewById(R.id.include_chat_img_position);
    }

    @Override
    public void initData() {
        JMessageClient.registerEventReceiver(this);
        Intent intent = getIntent();
        friendUid = intent.getIntExtra("friendUid", -1);
        userName = intent.getStringExtra("userName");
        if (userName == null || friendUid == -1)
            return;

        pre.getFriendInfo(friendUid, userName);
        pre.getFriendChatHis(friendUid, userName);
        //进入极光聊天
        JIMUtils.getJimUtils().gotoSingleChat(userName);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_chat_friend;
    }

    @Override
    public ActChatFriendPresenter initPresenter() {
        return new ActChatFriendPresenter();
    }

    /**
     * 获取聊天对象信息
     *
     * @param json friend info
     */
    public void setFriendInfo(String json) {
        friendInfoBean = new Gson().fromJson(json, JsonFriendInfoBean.class);
        if (friendInfoBean == null)
            return;
        tv_friendName.setText(friendInfoBean.getResult().getNickName());
    }

    /**
     * 获取聊天历史记录
     *
     * @param json history
     */
    public void setChatHis(String json) {
        JsonChatHisBean chatHisBean = new Gson().fromJson(json, JsonChatHisBean.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出聊天
        JIMUtils.getJimUtils().exitChat();
        JMessageClient.unRegisterEventReceiver(this);
    }


    public void onEvent(GetEventNotificationTaskMng.EventEntity event) {
        //do your own business
        String json = new Gson().toJson(event);
        Log.d(TAG, "onEventMainThread: " + json);
        Log.d(TAG, "onEvent: " + event.toString());
        Log.d(TAG, "onEvent: " + event.getConEventResponse());
        Log.d(TAG, "onEvent: " + event.getConvId());
        Log.d(TAG, "onEvent: " + event.getCallback());
    }

    public void onEventMainThread(GetEventNotificationTaskMng.EventEntity event) {
        //do your own business
        String json = new Gson().toJson(event);
        Log.d(TAG, "onEventMainThread: " + json);
        Log.d(TAG, "onEventMainThread: " + event.toString());
    }

        /**
         * 接收在线消息
         **/
        public void onEvent (MessageEvent event){
            //获取事件发生的会话对象
            String json = new Gson().toJson(event);
            Log.d(TAG, "onEventMainThread: " + json);
            //Conversation conversation = event.getConversation();
            Message newMessage = event.getMessage();//获取此次离线期间会话收到的新消息列表
            System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到一条来自%s的在线消息", newMessage));
        }

        /**
         * 接收离线消息。
         * 类似MessageEvent事件的接收，上层在需要的地方增加OfflineMessageEvent事件的接收
         * 即可实现离线消息的接收。
         **/
        public void onEvent(OfflineMessageEvent event){
            //获取事件发生的会话对象
            String json = new Gson().toJson(event);
            Log.d(TAG, "onEventMainThread: " + json);
            Conversation conversation = event.getConversation();
            List<Message> newMessageList = event.getOfflineMessageList();//获取此次离线期间会话收到的新消息列表
            System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到%d条来自%s的离线消息。\n", newMessageList.size(), conversation.getTargetId()));
        }

        /**
         * 接收消息漫游事件
         * 如果在JMessageClient.init时启用了消息漫游功能，则每当一个会话的漫游消息同步完成时
         * sdk会发送此事件通知上层。
         **/
        public void onEvent (ConversationRefreshEvent event){
            //获取事件发生的会话对象
            String json = new Gson().toJson(event);
            Log.d(TAG, "onEventMainThread: " + json);
            Conversation conversation = event.getConversation();
            //获取事件发生的原因，对于漫游完成触发的事件，此处的reason应该是 MSG_ROAMING_COMPLETE
            ConversationRefreshEvent.Reason reason = event.getReason();
            System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到ConversationRefreshEvent事件,待刷新的会话是%s.\n", conversation.getTargetId()));
            System.out.println("事件发生的原因 : " + reason);
        }
    }
