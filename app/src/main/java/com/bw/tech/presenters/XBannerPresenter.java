package com.bw.tech.presenters;

import android.widget.Toast;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.mylibrary.base.BasePresenter;
import com.bw.mylibrary.utils.InternetUtil;
import com.bw.mylibrary.utils.NetUtils;
import com.bw.tech.MyApp;
import com.bw.tech.Urls;
import com.bw.tech.fragments.MyInfomationFrag;

import java.util.HashMap;
import java.util.Map;

public class XBannerPresenter extends BasePresenter<MyInfomationFrag> {
    /*
    轮播图
     */
    public void getXBannerData(){
        //网络判断
        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE){
            NetUtils.getNetUtils().getInfo(Urls.XBanner_Url, new HashMap<String, Object>(), new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    //将拿到的数据返回到Fragment页面
                    iView.XBannerData(json);
                }
                @Override
                public void error() {

                }
            });

        }else{
            Toast.makeText(MyApp.context, "没网!玩您妈呢？", Toast.LENGTH_SHORT).show();

        }
    }

    /*
    咨讯信息列表
     */

    public void InformationData(){
        //判断网络
        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE){
            HashMap<String,Object> map=new HashMap<>();
//            map.put("plateId","10");
            map.put("page","1");
            map.put("count","5");
            //请求数据
            NetUtils.getNetUtils().getInfo(Urls.Information_Url, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    //将数据返回到Fragment
                    iView.InformationData(json);
                }

                @Override
                public void error() {

                }
            });
        }else{
            Toast.makeText(MyApp.context, "没网!玩您妈呢？", Toast.LENGTH_SHORT).show();

        }
    }

}