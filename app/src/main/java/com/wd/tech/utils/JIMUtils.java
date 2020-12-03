package com.wd.tech.utils;

import android.util.Log;
import android.widget.Toast;

import com.tencent.mmkv.MMKV;
import com.wd.mylibrary.bean.ConstantMMkv;
import com.wd.tech.MyApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:极光IM消息工具</p>
 *
 * @author Xaoyv
 * date 11/19/2020 2:34 PM
 */
public class JIMUtils {
    private final String TAG = "JIMUtils";
    private static JIMUtils jimUtils;
    private MMKV kv;

    private JIMUtils() {
        //单例模式
        kv = MMKV.defaultMMKV();
    }

    public static JIMUtils getJimUtils() {
        return jimUtils == null ? jimUtils = new JIMUtils() : jimUtils;
    }

    /**
     * 登录
     */
    public void login() {
        String username = kv.decodeString(ConstantMMkv.Key_UserName);
        String pwd = kv.decodeString(ConstantMMkv.Key_Jpwd);
        Log.d(TAG, "login: " + username + pwd);
        JMessageClient.login(username, pwd, new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0)
                    //s == Success 登录极光IM给你的账号  成功
                    Log.d(TAG, "gotResult: " + s);
                else
                    Toast.makeText(MyApp.context, "极光服务初始化异常，无法聊天", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "gotResult: " + i);
            }
        });
    }

    /**
     * 登出
     */
    public void logout() {
        JMessageClient.logout();
    }

    /**
     * 获取其他用户信息
     *
     * @param callback /接口回调
     */
    public void getOtherUserInfo(String userName, GetUserInfoCallback callback) {
        JMessageClient.getUserInfo(userName, callback);
    }

    /**
     * 获取我的信息
     *
     * @return user info
     */
    public UserInfo getLocalUserInfo() {
        return JMessageClient.getMyInfo();
    }

    /**
     * 获取会话列表
     *
     * @return 会话列表
     */
    public List<Conversation> getChatList() {
        return JMessageClient.getConversationList();
    }

    /**
     * 进入单聊页面
     *
     * @param userName 要聊天的对象
     */
    public void gotoSingleChat(String userName) {
        //先创建单聊会话
        Conversation.createSingleConversation(userName);
        JMessageClient.enterSingleConversation(userName);
    }

    /**
     * 进入群聊界面
     *
     * @param groupId 群聊ID
     */
    public void gotoGroupChat(long groupId) {
        //先创建群聊对话
        Conversation.createGroupConversation(groupId);
        JMessageClient.enterGroupConversation(groupId);
    }

    /**
     * 退出聊天
     * 在退出聊天界面的时候进行调用
     */
    public void exitChat() {
        JMessageClient.exitConversation();
        //重新设置通知类型
        JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_SOUND);
    }

    /**
     * 获取好友聊天会话对象
     *
     * @param userName 好友用户名
     * @return 会话
     */
    public Conversation getChatInfo(String userName) {
        return JMessageClient.getSingleConversation(userName);
    }

    /**
     * 获取群聊会话对象
     *
     * @param groupId id
     * @return 会话
     */
    public Conversation getChatInfo(long groupId) {
        return JMessageClient.getGroupConversation(groupId);
    }

    /**
     * 删除私聊
     *
     * @param userName username
     */
    public void delChat(String userName) {
        JMessageClient.deleteSingleConversation(userName);
    }

    /**
     * 删除群聊会话
     *
     * @param groupId id
     */
    public void delChat(long groupId) {
        JMessageClient.deleteGroupConversation(groupId);
    }

    /**
     * 获取某个聊天的未读消息数量
     *
     * @param conversation 会话
     * @return 0
     */
    public int getUnreadMsgCount(Conversation conversation) {
        return conversation.getUnReadMsgCnt();
    }

    /**
     * 获取未读消息总数
     *
     * @return TabLayout 试试设置角标
     */
    public int getAllUnreadMsgCount() {
        return JMessageClient.getAllUnReadMsgCount();
    }

    /**
     * 标记未读
     *
     * @param conversation 会话对象
     * @return 有没有设置成功
     */
    public boolean setConverUnread(Conversation conversation) {
        //暂时设置成1，表示未读
        return conversation.setUnReadMessageCnt(1);
    }

    /**
     * create single text massage
     *
     * @param userName username
     * @param text     text
     * @return msg
     */
    public Message createTextMsg(String userName, String text) {
        return JMessageClient.createSingleTextMessage(userName, text);
    }

    /**
     * create group's text msg
     *
     * @param groupId id
     * @param text    text
     * @return msg
     */
    public Message createTextMsg(long groupId, String text) {
        return JMessageClient.createGroupTextMessage(groupId, text);
    }

    /**
     * 创建图片消息
     * 需要进行非空判断
     *
     * @param userName 好友昵称
     * @param img      图片文件 file
     * @return null 非空判断
     */
    public Message createImgMsg(String userName, File img) {
        try {
            return JMessageClient.createSingleImageMessage(userName, img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建群聊图片消息
     * 需要进行非空判断
     *
     * @param groupId id
     * @param img     图片文件 file
     * @return null 非空判断
     */
    public Message createImgMsg(long groupId, File img) {
        try {
            return JMessageClient.createGroupImageMessage(groupId, img);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建单聊语音消息
     *
     * @param userName username
     * @param voice    语音文件 理想格式 amr
     * @param time     录音时长
     * @return msg
     */
    public Message createVoiceMsg(String userName, File voice, int time) {
        try {
            return JMessageClient.createSingleVoiceMessage(userName, voice, time);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 创建群聊语音消息
     *
     * @param groupId id
     * @param voice   语音文件
     * @param time    录音时长
     * @return msg
     */
    public Message createVoiceMsg(long groupId, File voice, int time) {
        try {
            return JMessageClient.createGroupVoiceMessage(groupId, voice, time);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 发送消息
     *
     * @param msg      消息
     * @param callback 监听
     */
    public void sendMessage(Message msg, BasicCallback callback) {
        msg.setOnSendCompleteCallback(callback);
        JMessageClient.sendMessage(msg);
    }

    /**
     * 获取极光好友列表
     *
     * @param callback
     */
    public void getFriendList(GetUserInfoListCallback callback) {
        ContactManager.getFriendList(callback);
    }

    /**
     * 添加好友
     *
     * @param userName 好友的用户名
     * @param reason   验证消息
     * @param callback 接口回调
     */
    public void addFriend(String userName, String reason, BasicCallback callback) {
        //s1 表示Appkey，为空默认本应用
        ContactManager.sendInvitationRequest(userName, null, reason, callback);
    }

    /**
     * 同意好友请求
     *
     * @param userName 好友用户名
     * @param callback 接口回调
     */
    public void agreeAddRequest(String userName, BasicCallback callback) {
        ContactManager.acceptInvitation(userName, null, callback);
    }

    /**
     * 拒绝好友请求
     *
     * @param userName username
     * @param reason   拒绝理由
     * @param callback 接口回调
     */
    public void refuseAddRequest(String userName, String reason, BasicCallback callback) {
        //s1表示Appkey
        ContactManager.declineInvitation(userName, null, reason, callback);
    }

    /**
     * 删除好友
     *
     * @param userInfo user info
     * @param callback callback
     */
    public void deleteFriend(UserInfo userInfo, BasicCallback callback) {
        userInfo.removeFromFriendList(callback);
    }

    /**
     * 更新用户备注
     *
     * @param userInfo  用户信息
     * @param newRemark 新的备注信息
     * @param callback  回调方法
     */
    public void updateFriendRemark(UserInfo userInfo, String newRemark, BasicCallback callback) {
        userInfo.updateNoteName(newRemark, callback);
    }
}
