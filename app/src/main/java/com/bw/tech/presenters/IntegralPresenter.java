package com.bw.tech.presenters;

import android.widget.Toast;

import com.bw.mylibrary.base.BasePresenter;
import com.bw.mylibrary.utils.InternetUtil;
import com.bw.mylibrary.utils.NetUtils;
import com.bw.tech.MyApp;
import com.bw.tech.Urls;
import com.bw.tech.activities.IntegralActivity;

import java.util.HashMap;

public class IntegralPresenter extends BasePresenter<IntegralActivity> {
    public void getIntegralData(){
        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE){
            NetUtils.getNetUtils().getInfo(Urls.Integral_Url, new HashMap<String, Object>(), new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    iView.Integral(json);
                }

                @Override
                public void error() {

                }
            });
        }else{
            Toast.makeText(MyApp.context, "无网！", Toast.LENGTH_SHORT).show();
        }
    }
}
