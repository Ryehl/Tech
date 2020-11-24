package com.wd.tech.presenters;

import android.widget.Toast;

import com.google.gson.Gson;
import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.mylibrary.utils.encrypt.RsaCoder;
import com.wd.tech.activities.FaceRecognitionActivity;
import com.wd.tech.beans.JsonBindFaceIdBean;

import java.util.HashMap;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:人脸识别</p>
 *
 * @author Xaoyv
 * date 11/24/2020 8:06 AM
 */
public class ActFaceRecognitionPresenter extends BasePresenter<FaceRecognitionActivity> {
    public void getCouldFaceId() {
        NetUtils.getNetUtils().putInfo("user/verify/v1/bindingFaceId", new HashMap<>(), new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                JsonBindFaceIdBean bindFaceIdBean = new Gson().fromJson(json, JsonBindFaceIdBean.class);
                if (bindFaceIdBean == null)
                    return;
                String faceId = bindFaceIdBean.getFaceId();
                if (faceId == null) {
                    Toast.makeText(iView, bindFaceIdBean.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    String face = RsaCoder.decryptByPublicKey(faceId);
                    iView.save2realm(face);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error() {
            }
        });
    }
}
