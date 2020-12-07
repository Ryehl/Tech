package com.wd.tech.presenters;

import com.google.gson.Gson;
import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.AddfriendActivity;
import com.wd.tech.beans.JsonMsgAndStatusBean;

import java.util.HashMap;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:添加好友</p>
 *
 * @author Xaoyv
 * date 11/16/2020 8:21 AM
 */
public class ActAddfriendPresenter extends BasePresenter<AddfriendActivity> {
    public void addFriend(int friendUid, String remark) {
        if (InternetUtil.getNetworkState(iView) == InternetUtil.NETWORN_NONE)
            return;
        HashMap<String, Object> map = new HashMap<>();
        map.put("friendUid", friendUid);
        map.put("remark", remark);
        NetUtils.getNetUtils().postInfo(Urls.Add_Friend, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                if (iView == null)
                    return;
                JsonMsgAndStatusBean bean = new Gson().fromJson(json, JsonMsgAndStatusBean.class);
                iView.addCallback(bean);
            }

            @Override
            public void error() {
            }
        });
    }

    public void getFriendInfo(int friendUid) {
        if (InternetUtil.getNetworkState(iView) == InternetUtil.NETWORN_NONE)
            return;
        HashMap<String, Object> map = new HashMap<>();
        map.put("friend", friendUid);
        NetUtils.getNetUtils().getInfo(Urls.QueryFriendById, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.setFriendInfo(json);
            }

            @Override
            public void error() {
            }
        });
    }
}
