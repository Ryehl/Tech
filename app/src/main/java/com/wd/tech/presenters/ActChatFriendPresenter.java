package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.ChatFriendActivity;

import java.util.HashMap;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:好友聊天</p>
 *
 * @author Xaoyv
 * date 11/25/2020 2:04 PM
 */
public class ActChatFriendPresenter extends BasePresenter<ChatFriendActivity> {
    public void getFriendInfo(int friendUid, String userName) {
        if (InternetUtil.getNetworkState(iView) == InternetUtil.NETWORN_NONE)
            return;
        HashMap<String, Object> map = new HashMap<>();
        map.put("friend", friendUid);
        NetUtils.getNetUtils().getInfo(Urls.QueryFriendById, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                if (iView == null)
                    return;
                iView.setFriendInfo(json);
            }

            @Override
            public void error() {
            }
        });
    }

    public void getFriendChatHis(int friendUid, String userName) {
        if (InternetUtil.getNetworkState(iView) == InternetUtil.NETWORN_NONE)
            return;
        HashMap<String, Object> map = new HashMap<>();
        map.put("friendUid", friendUid);
        //TODO 提取Count和Page
        map.put("page", 1);
        map.put("count", 10);
        NetUtils.getNetUtils().getInfo(Urls.QueryChatHis, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                if (iView == null)
                    return;
                iView.setChatHis(json);
            }

            @Override
            public void error() {
            }
        });
    }
}
