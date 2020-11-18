package com.bw.tech.presenters;

import android.util.Log;
import android.widget.Toast;

import com.bw.mylibrary.base.BasePresenter;
import com.bw.mylibrary.bean.Constant;
import com.bw.mylibrary.utils.NetUtils;
import com.bw.tech.Urls;
import com.bw.tech.activities.MainActivity;
import com.bw.tech.activities.PublishActivity;
import com.bw.tech.beans.JsonSendBean;
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
        ArrayList<File> images = new ArrayList<>();
        //add images
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (String path : images_list) {
            File file = new File(path);
            images.add(file);
            RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), reqBody);
            parts.add(part);
        }

        //begin
        NetUtils.getNetUtils().upLoadFiles(Urls.tz_dtsc, content, parts, new NetUtils.GetJsonListener() {
            @Override
            public void success(String json) {
                JsonSendBean sendBean = new Gson().fromJson(json, JsonSendBean.class);
                if (iView != null)
                    iView.uploadImagesMsg(sendBean.getMessage());
            }

            @Override
            public void error() {
                if (iView != null)
                    iView.uploadImagesMsg("上传失败");
            }
        });
    }
}
