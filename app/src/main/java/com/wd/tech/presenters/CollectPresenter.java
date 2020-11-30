package com.wd.tech.presenters;

import android.widget.Toast;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.MyApp;
import com.wd.tech.Urls;
import com.wd.tech.activities.CollectActivity;

import java.util.HashMap;

public class CollectPresenter extends BasePresenter<CollectActivity> {
    //展示收藏
    public void getCollectData() {
        if (InternetUtil.getNetworkState(MyApp.context) != InternetUtil.NETWORN_NONE) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("page", "1");
            map.put("count", "5");
            NetUtils.getNetUtils().getInfo(Urls.Collect_Url, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    iView.CollectData(json);
                }

                @Override
                public void error() {

                }
            });
        } else {
            Toast.makeText(iView, "无网！", Toast.LENGTH_SHORT).show();
        }
    }

    //删除收藏
    public void getDeleteCollectData(){
        if (InternetUtil.getNetworkState(MyApp.context) != InternetUtil.NETWORN_NONE) {

            NetUtils.getNetUtils().getInfo(Urls.UnCollect_Url, new HashMap<String, Object>(), new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    iView.CollectData(json);
                }

                @Override
                public void error() {

                }
            });
        } else {
            Toast.makeText(iView, "无网！", Toast.LENGTH_SHORT).show();
        }
    }
}
