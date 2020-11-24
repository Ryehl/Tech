package com.wd.tech.presenters;

import android.widget.Toast;

import com.google.gson.Gson;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.activities.SettingActivity;
import com.wd.mylibrary.base.BasePresenter;
import com.wd.tech.beans.JsonUnbindFaceIdBean;

import java.util.HashMap;

public class SettingPresenter extends BasePresenter<SettingActivity> {
    public void getSettingData() {
    }

    public void unBindFaceId() {
        NetUtils.getNetUtils().deleteInfo("user/verify/v1/untiedFaceId", new HashMap<>(), new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                if (iView == null)
                    return;
                JsonUnbindFaceIdBean unbindFaceIdBean = new Gson().fromJson(json, JsonUnbindFaceIdBean.class);
                if (unbindFaceIdBean == null) {
                    Toast.makeText(iView, "解绑失败", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(iView, unbindFaceIdBean.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void error() {
            }
        });
    }
}
