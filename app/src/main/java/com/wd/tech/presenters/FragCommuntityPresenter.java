package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.fragments.MainCommuntityFrag;

import java.util.HashMap;

public class FragCommuntityPresenter extends BasePresenter<MainCommuntityFrag> {
    public void CommunityData(){
        HashMap<String,Object> map=new HashMap<>();
        map.put("page","1");
        map.put("count","5");
        NetUtils.getNetUtils().getInfo(Urls.Community_Url, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.CommunityData(json);
            }

            @Override
            public void error() {

            }
        });
    }
}
