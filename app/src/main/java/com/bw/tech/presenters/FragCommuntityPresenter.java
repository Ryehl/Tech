package com.bw.tech.presenters;

import com.bw.mylibrary.base.BasePresenter;
import com.bw.mylibrary.utils.NetUtils;
import com.bw.tech.Urls;
import com.bw.tech.fragments.MainCommuntityFrag;

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
