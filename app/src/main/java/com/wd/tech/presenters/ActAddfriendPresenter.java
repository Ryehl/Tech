package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.activities.AddfriendActivity;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:添加好友</p>
 *
 * @author Xaoyv
 * date 11/16/2020 8:21 AM
 */
public class ActAddfriendPresenter extends BasePresenter<AddfriendActivity> {
    public void addFriend() {
        if (InternetUtil.getNetworkState(iView) == InternetUtil.NETWORN_NONE)
            return;
        //NetUtils.getNetUtils().
    }
}
