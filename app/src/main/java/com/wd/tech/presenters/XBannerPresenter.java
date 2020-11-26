package com.wd.tech.presenters;

import android.widget.Toast;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.MyApp;
import com.wd.tech.Urls;
import com.wd.tech.fragments.MainInfomationFrag;

import java.util.HashMap;

public class XBannerPresenter extends BasePresenter<MainInfomationFrag> {
    /*
    轮播图
     */
    public void getXBannerData() {
        //网络判断
        if (InternetUtil.getNetworkState(MyApp.context) != InternetUtil.NETWORN_NONE) {
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

        } else {
            Toast.makeText(MyApp.context, "没网!玩您妈呢？", Toast.LENGTH_SHORT).show();

        }
    }

    /*
    咨讯信息列表
     */

    public void InformationData(int page,int count) {
        //判断网络
        if (InternetUtil.getNetworkState(MyApp.context) != InternetUtil.NETWORN_NONE) {
            HashMap<String, Object> map = new HashMap<>();
//            map.put("plateId","10");
            map.put("page", page+"");
            map.put("count", count+"");
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
        } else {
            Toast.makeText(MyApp.context, "没网!玩您妈呢？", Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * 点赞
     */
    public void getPraiseData(int index){
        HashMap<String,Object> map=new HashMap<>();
        map.put("infoId",index);
        NetUtils.getNetUtils().postInfo(Urls.AddCollection_Url, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.PraiseData(json);
            }

            @Override
            public void error() {

            }
        });
    }
}
