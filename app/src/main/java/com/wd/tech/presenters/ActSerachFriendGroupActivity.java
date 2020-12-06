package com.wd.tech.presenters;

import android.util.Log;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.SearchFriendGroupActivity;

import java.util.HashMap;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:搜索好友群聊</p>
 *
 * @author Xaoyv
 * date 11/30/2020 3:22 PM
 */
public class ActSerachFriendGroupActivity extends BasePresenter<SearchFriendGroupActivity> {

    private final String TAG = "ActSerachFriendGroupActivity";

    public void searchFirend(String input) {
        if (InternetUtil.getNetworkState(iView)  == InternetUtil.NETWORN_NONE)
            return;
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", input);
        NetUtils.getNetUtils().getInfo(Urls.QueryFriendById, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                Log.d(TAG, "success: " + json);
            }

            @Override
            public void error() {
            }
        });
    }
}
