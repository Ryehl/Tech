package com.bw.tech.presenters;

import android.util.Log;
import android.widget.Toast;

import com.bw.mylibrary.base.BasePresenter;
import com.bw.mylibrary.utils.InternetUtil;
import com.bw.mylibrary.utils.NetUtils;
import com.bw.tech.Urls;
import com.bw.tech.fragments.MsgContantFrag;

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
