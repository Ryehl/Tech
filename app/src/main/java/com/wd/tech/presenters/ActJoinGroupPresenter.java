package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.JoinGroupActivity;

import java.util.HashMap;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:加入群聊</p>
 *
 * @author Xaoyv
 * date 11/25/2020 7:58 PM
 */
public class ActJoinGroupPresenter extends BasePresenter<JoinGroupActivity> {
    public void joinGroup(int groupId) {
        if (InternetUtil.getNetworkState(iView) == InternetUtil.NETWORN_NONE)
            return;
        //TODO 服务器加群
        HashMap<String, Object> map = new HashMap<>();
        map.put("groupId", groupId);
        NetUtils.getNetUtils().postInfo(Urls.JoinGroup, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.joinGroupSuccess(json);
            }

            @Override
            public void error() {
            }
        });
        //TODO 极光 加群
    }
}
