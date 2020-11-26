package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.ContactInfoActivity;

import java.util.HashMap;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:联系人信息s</p>
 *
 * @author Xaoyv
 * date 11/24/2020 3:09 PM
 */
public class ActContactnInfoPresenter extends BasePresenter<ContactInfoActivity> {
    public void getFriendInfoByUid(int friendUid) {
        if (InternetUtil.getNetworkState(iView) == InternetUtil.NETWORN_NONE)
            return;
        HashMap<String, Object> map = new HashMap<>();
        map.put("friend", friendUid);
        NetUtils.getNetUtils().getInfo(Urls.QueryFriendById, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                if (iView != null)
                    iView.setData(json);
            }

            @Override
            public void error() {
            }
        });
    }

    public void checkIsMyFriend(int friendUid) {
        if (InternetUtil.getNetworkState(iView) != InternetUtil.NETWORN_NONE) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("friendUid", friendUid);
            NetUtils.getNetUtils().getInfo(Urls.CheckMyFriend, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    if (iView != null)
                        iView.changeBtn(json);
                }

                @Override
                public void error() {
                }
            });
        }
    }
}
