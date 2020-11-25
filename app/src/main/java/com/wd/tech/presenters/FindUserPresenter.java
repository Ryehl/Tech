package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.FindUserActivity;

import java.util.HashMap;

public class FindUserPresenter extends BasePresenter<FindUserActivity> {

    public void getFindUserData(int id){
        HashMap<String,Object> map=new HashMap<>();
        map.put("fromUid",id);
        map.put("page","1");
        map.put("count","5");
        NetUtils.getNetUtils().getInfo(Urls.FindUser_Url, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                //iView.FindUserData(json);
            }

            @Override
            public void error() {

            }
        });
    }
}
