package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.GroupInfoActivity;

import java.util.HashMap;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:群聊信息</p>
 *
 * @author Xaoyv
 * date 11/25/2020 2:25 PM
 */
public class ActGroupInfoPresenter extends BasePresenter<GroupInfoActivity> {
    public void getGroupInfo(int groupId) {
        if (InternetUtil.getNetworkState(iView) == InternetUtil.NETWORN_NONE)
            return;
        HashMap<String, Object> map = new HashMap<>();
        map.put("groupId", groupId);
        NetUtils.getNetUtils().getInfo(Urls.Get_GroupInfo, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.setData(json);
            }

            @Override
            public void error() {
            }
        });
    }

    /**
     * 检查我又没有在这个群里
     *
     * @param groupId id
     */
    public void checkInGroup(int groupId) {
        if (InternetUtil.getNetworkState(iView) == InternetUtil.NETWORN_NONE)
            return;
        HashMap<String, Object> map = new HashMap<>();
        map.put("groupId", groupId);
        NetUtils.getNetUtils().getInfo(Urls.Check_IsInGroup, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.setBtn(json);
            }

            @Override
            public void error() {
            }
        });
    }
}
