package com.wd.tech.presenters;

import android.widget.Toast;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.MyApp;
import com.wd.tech.Urls;
import com.wd.tech.activities.IntegralActivity;

import java.util.HashMap;

public class IntegralPresenter extends BasePresenter<IntegralActivity> {
    public void getIntegralData(){
        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE){
            NetUtils.getNetUtils().getInfo(Urls.Integral_Url, new HashMap<String, Object>(), new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    iView.IntegralData(json);
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
