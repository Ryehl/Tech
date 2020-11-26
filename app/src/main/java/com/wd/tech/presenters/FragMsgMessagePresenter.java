package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.tech.fragments.MsgMessageFrag;
import com.wd.tech.utils.JIMUtils;

import java.util.List;

import cn.jpush.im.android.api.model.Conversation;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:消息列表</p>
 *
 * @author Xaoyv
 * date 11/13/2020 6:51 PM
 */
public class FragMsgMessagePresenter extends BasePresenter<MsgMessageFrag> {
    public void getMessageList() {
        if (InternetUtil.getNetworkState(iView.getContext()) == InternetUtil.NETWORN_NONE)
            return;
        List<Conversation> chatList = JIMUtils.getJimUtils().getChatList();
        iView.setAdap(chatList);
    }
}
