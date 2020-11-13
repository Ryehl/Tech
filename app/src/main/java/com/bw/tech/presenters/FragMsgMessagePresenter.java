package com.bw.tech.presenters;

import com.bw.mylibrary.base.BasePresenter;
import com.bw.mylibrary.utils.InternetUtil;
import com.bw.mylibrary.utils.NetUtils;
import com.bw.tech.fragments.MsgMessageFrag;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:消息列表</p>
 *
 * @author Xaoyv
 * date 11/13/2020 6:51 PM
 */
public class FragMsgMessagePresenter extends BasePresenter<MsgMessageFrag> {
    public void getMessageList(){
        if (InternetUtil.getNetworkState(iView.getContext()) != InternetUtil.NETWORN_NONE){
            //
        }
    }
}
