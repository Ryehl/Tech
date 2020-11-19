package com.wd.tech.presenters;

import android.util.Log;
import android.widget.Toast;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.beans.JsonFriendListBean;
import com.wd.tech.fragments.MsgContantFrag;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:联系人</p>
 *
 * @author Xaoyv
 * date 11/13/2020 7:12 PM
 */
public class FragMsgContantPresenter extends BasePresenter<MsgContantFrag> {
    private final String TAG = "FragMsgContantPresenter";

    public void initFriendList() {
        if (InternetUtil.getNetworkState(iView.getContext()) != InternetUtil.NETWORN_NONE) {
            NetUtils.getNetUtils().getInfo(Urls.Friend_List, new HashMap<>(), new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    Log.d(TAG, "success: " + json);
                    JsonFriendListBean friendListBean = new Gson().fromJson(json, JsonFriendListBean.class);
                    if (iView != null)
                        iView.setElvAdap(friendListBean);
                }

                @Override
                public void error() {
                    if (iView == null)
                        return;
                    Toast.makeText(iView.getContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
