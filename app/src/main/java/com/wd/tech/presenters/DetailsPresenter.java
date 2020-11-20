package com.wd.tech.presenters;

import android.widget.Toast;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.MyApp;
import com.wd.tech.Urls;
import com.wd.tech.activities.DetailsActivity;

import java.util.HashMap;

public class DetailsPresenter extends BasePresenter<DetailsActivity> {
    //详情数据
    public void getDetailsData(int id){
        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE){
            HashMap<String,Object> map=new HashMap<>();
            map.put("id",id);
            NetUtils.getNetUtils().getInfo(Urls.Details_Url, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    iView.DetailsData(json);
                }

                @Override
                public void error() {
                }
            });
        }else{
            Toast.makeText(iView, "无网！", Toast.LENGTH_SHORT).show();
        }
    }

    //详情页的评论
    public void getDetailsCommentData(int index) {
        if (InternetUtil.getNetworkState(MyApp.context) != InternetUtil.NETWORN_NONE) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("page", "1");
            map.put("count", "15");
            map.put("infoId",index);
            NetUtils.getNetUtils().getInfo(Urls.DetailsComment_Url, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    iView.DetailsCommentData(json);
                }

                @Override
                public void error() {
                }
            });
        } else {
            Toast.makeText(MyApp.context, "无网！", Toast.LENGTH_SHORT).show();
        }
    }
}
