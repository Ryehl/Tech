package com.bw.tech.presenters;

import com.bw.mylibrary.base.BasePresenter;
import com.bw.mylibrary.utils.InternetUtil;
import com.bw.mylibrary.utils.NetUtils;
import com.bw.tech.MyApp;
import com.bw.tech.activities.CollectActivity;

import java.util.HashMap;

public class CollectPresenter extends BasePresenter<CollectActivity> {
    public void CollectData(){
        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE ){
            HashMap<String,Object> map=new HashMap<>();
            map.put("page","1");
            map.put("count","5");
            //NetUtils.getNetUtils().getInfo();
        }
    }
}
