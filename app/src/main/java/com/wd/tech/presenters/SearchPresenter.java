package com.wd.tech.presenters;

import android.widget.Toast;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.MyApp;
import com.wd.tech.Urls;
import com.wd.tech.activities.SearchActivity;

import java.util.HashMap;

public class SearchPresenter extends BasePresenter<SearchActivity> {

    //根据文章查询
    public void getSearchArticleData(String keyword){
        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE){
            HashMap<String,Object> map=new HashMap<>();
            map.put("title",keyword);
            map.put("page","1");
            map.put("count","5");
            NetUtils.getNetUtils().getInfo(Urls.Article_Url, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    iView.SearchArticleData(json);
                }

                @Override
                public void error() {

                }
            });

        }else {
            Toast.makeText(MyApp.context, "无网！", Toast.LENGTH_SHORT).show();
        }
    }

    //根据作者查询
    public void getSearchAuthorData(String keyword){
        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE){
            HashMap<String,Object> map=new HashMap<>();
            map.put("source",keyword);
            map.put("page","1");
            map.put("count","5");
            NetUtils.getNetUtils().getInfo(Urls.Author_Url, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    iView.SearchAuthorData(json);
                }

                @Override
                public void error() {

                }
            });
        }else {
            Toast.makeText(MyApp.context, "无网！", Toast.LENGTH_SHORT).show();
        }
    }

}
