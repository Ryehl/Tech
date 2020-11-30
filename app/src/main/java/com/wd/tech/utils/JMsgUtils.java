package com.wd.tech.utils;

import cn.jpush.im.android.api.content.FileContent;
import cn.jpush.im.android.api.content.ImageContent;
import cn.jpush.im.android.api.content.LocationContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.content.VideoContent;
import cn.jpush.im.android.api.content.VoiceContent;
import cn.jpush.im.android.api.model.Message;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:消息工具类</p>
 *
 * @author Xaoyv
 * date 11/28/2020 7:57 AM
 */
public class JMsgUtils {
    private static JMsgUtils jMsgUtils;

    private JMsgUtils() {
    }

    //单例模式
    public static JMsgUtils getjMsgUtils() {
        return jMsgUtils = jMsgUtils == null ? new JMsgUtils() : jMsgUtils;
    }

    public String getMessageContent(Message msg) {
        switch (msg.getContentType()) {
            case text:
                TextContent textContent = (TextContent) msg.getContent();
                return textContent.getText();
            case file:
                FileContent fileContent = (FileContent) msg.getContent();
                return "[文件]" + fileContent.getFileName();
            case image:
                ImageContent imageContent = (ImageContent) msg.getContent();
                return "[图片]";
            case video:
                VideoContent content = (VideoContent) msg.getContent();
                return "[视频]" + content.getFileName();
            case voice:
                VoiceContent msgContent = (VoiceContent) msg.getContent();
                return "[语音消息]";
            case location:
                LocationContent locationContent = (LocationContent) msg.getContent();
                return "[位置]";
            case eventNotification:
                //事件通知
            case unknown:
                //未知类型消息
            case prompt:
                //提示消息
            case custom:
                //自定义消息
            default:
                return "[不支持的消息类型]";
        }
    }
}
