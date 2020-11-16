package com.bw.tech.presenters;

import android.widget.Toast;

import com.bw.mylibrary.base.BasePresenter;
import com.bw.mylibrary.utils.InternetUtil;
import com.bw.mylibrary.utils.NetUtils;
import com.bw.tech.MyApp;
import com.bw.tech.Urls;
import com.bw.tech.activities.NoticeActivity;

import java.util.HashMap;

public class NoticePresenter extends BasePresenter<NoticeActivity> {

    public void getNoticeData(){
        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE){
            HashMap<String,Object> map=new HashMap<>();
            map.put("page","1");
            map.put("count","5");
            NetUtils.getNetUtils().getInfo(Urls.Notice_Url, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    iView.NoticeData(json);
                }

                @Override
                public void error() {

                }
            });
        }else{
            Toast.makeText(MyApp.context, "没网", Toast.LENGTH_SHORT).show();
        }
    }
}
