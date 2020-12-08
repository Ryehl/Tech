package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.FriendRequestNoticeActivity;

import java.util.HashMap;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:好友申请通知</p>
 *
 * @author Xaoyv
 * date 12/7/2020 4:53 PM
 */
public class ActFriendRequestNoticePresenter extends BasePresenter<FriendRequestNoticeActivity> {
    public void getNotices() {
        if (InternetUtil.getNetworkState(iView) == InternetUtil.NETWORN_NONE)
            return;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("count", 10);
        NetUtils.getNetUtils().getInfo(Urls.Query_FriendNotice, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.showNotic(json);
            }

            @Override
            public void error() {
            }
        });
    }
}
