package com.wd.tech.presenters;

import android.widget.Toast;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.MyApp;
import com.wd.tech.Urls;
import com.wd.tech.activities.AttentionActivity;

import java.util.HashMap;

public class AttentionPresenter extends BasePresenter<AttentionActivity> {

    public void getAttentionData(){
        //判断网络
        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE){
            HashMap<String,Object> map=new HashMap<>();
            map.put("page","1");
            map.put("count","5");
            //网络请求
            NetUtils.getNetUtils().getInfo(Urls.Attention_Url, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    iView.AttentionData(json);
                }

                @Override
                public void error() {

                }
            });
        }else{
            Toast.makeText(MyApp.context, "没网玩你妈？", Toast.LENGTH_SHORT).show();
        }
    }

}
