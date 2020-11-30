package com.wd.tech.activities;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.bean.ConstantMMkv;
import com.wd.tech.R;
import com.wd.tech.adapters.ChatViewRecyAdap;
import com.wd.tech.beans.JsonChatHisBean;
import com.wd.tech.beans.JsonFriendInfoBean;
import com.wd.tech.presenters.ActChatFriendPresenter;
import com.wd.tech.utils.JIMUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import cn.jmessage.biz.httptask.task.GetEventNotificationTaskMng;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

public class ChatFriendActivity extends BaseActivity<ActChatFriendPresenter> {

    private int friendUid;//服务器好友ID
    private String userName;//极光用户名
    private String TAG = getClass().getName();

    private TextView tv_friendName;
    private RecyclerView recy_showChat;
    private EditText et_input;
    private Button btn_send;
    private ImageView img_vol;
    private ImageView img_img;
    private ImageView img_camera;
    private ImageView img_position;
    private JsonFriendInfoBean friendInfoBean;
    private JIMUtils jimUtils;
    private Conversation chatInfo;
    private MMKV kv;
    private ChatViewRecyAdap adapter;
    private List<Message> allMessage;

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
        kv = MMKV.defaultMMKV();
        //获取重要信息
        Intent intent = getIntent();
        friendUid = intent.getIntExtra("friendUid", -1);
        userName = intent.getStringExtra("userName");
        if (userName == null || friendUid == -1) {
            //userName 和 friendUid缺一不可
            Toast.makeText(this, "获取信息失败", Toast.LENGTH_SHORT).show();
            finish();
        }

        pre.getFriendInfo(friendUid);
        //进入极光聊天
        JMessageClient.registerEventReceiver(this);
        jimUtils = JIMUtils.getJimUtils();
        jimUtils.gotoSingleChat(userName);

        //发送消息的监听
        btn_send.setOnClickListener(v -> {
            String input = et_input.getText().toString().trim();
            if (input.length() == 0)
                return;
            et_input.setText("");
            Message msg = jimUtils.createTextMsg(userName, input);
            jimUtils.sendMessage(msg, new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    if (i == 0)
                        recyScroll(msg);
                    //TODO 消息发送失败
                }
            });
        });
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
        //先获取完信息之后再展示
        showChatHis();
    }

    /**
     * 展示聊天记录
     */
    public void showChatHis() {
        //头像列表
        HashMap<String, String> map = new HashMap<>();
        map.put(userName, friendInfoBean.getResult().getHeadPic());
        //mmkv sp 获取自己的username 和 头像
        map.put(kv.decodeString(ConstantMMkv.Key_UserName), kv.decodeString(ConstantMMkv.Key_HeadPic));
        if (jimUtils == null)
            jimUtils = JIMUtils.getJimUtils();
        //获取会话对象
        chatInfo = jimUtils.getChatInfo(userName);
        //获取所有聊天
        allMessage = chatInfo.getAllMessage();
        //展示到适配器
        recy_showChat.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatViewRecyAdap(allMessage, map);
        recy_showChat.setAdapter(adapter);
        recy_showChat.scrollToPosition(allMessage.size() - 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出聊天
        JIMUtils.getJimUtils().exitChat();
    }

    /**
     * 接收在线消息
     **/
    public void onEvent(MessageEvent event) {
        //获取事件发生的会话对象
        //Conversation conversation = event.getConversation();
        Message msg = event.getMessage();//获取此次离线期间会话收到的新消息列表
        recyScroll(msg);
    }

    /**
     * 把消息放到集合里
     * 然后刷新适配器
     * 然后滚动到最后一行
     *
     * @param msg
     */
    private void recyScroll(Message msg) {
        allMessage.add(msg);
        adapter.notifyDataSetChanged();
        recy_showChat.scrollToPosition(allMessage.size() - 1);
    }
}
