package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.AlterSignatureActivity;

import java.util.HashMap;

public class AlterSignaturePresenter extends BasePresenter<AlterSignatureActivity> {

    public void getAlterSignatureData(String signature){
        HashMap<String,Object> map=new HashMap<>();
        map.put("signature",signature);
        NetUtils.getNetUtils().putInfo(Urls.AlterSignature_Url, map, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                iView.AlterSignatureData(json);
            }

            @Override
            public void error() {

            }
        });
    }
}
