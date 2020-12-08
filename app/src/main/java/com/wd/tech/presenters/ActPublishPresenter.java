package com.wd.tech.presenters;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.Urls;
import com.wd.tech.activities.PublishActivity;
import com.wd.tech.beans.JsonMsgAndStatusBean;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:up load images presenter</p>
 *
 * @author Xaoyv
 * date 11/18/2020 8:54 AM
 */
public class ActPublishPresenter extends BasePresenter<PublishActivity> {
    private final String TAG = "ActPublishPresenter";

    public void uploadImages(String content, List<String> images_list) {
        //add images
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (String path : images_list) {
            File file = new File(path);
            RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), reqBody);
            parts.add(part);
        }

        //begin 开始调接口上传
        NetUtils.getNetUtils().upLoadFiles(Urls.tz_dtsc, content, parts, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                //success
                JsonMsgAndStatusBean sendBean = new Gson().fromJson(json, JsonMsgAndStatusBean.class);
                if (iView != null)
                    iView.uploadImagesMsg(sendBean.getMessage());
                iView.finish();
            }

            @Override
            public void error() {
                if (iView != null)
                    iView.uploadImagesMsg("上传失败");
            }
        });
    }
}
