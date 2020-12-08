package com.wd.tech.presenters;

import android.widget.Toast;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.activities.RegisterActivity;
import com.wd.tech.beans.RegisterBean;
import com.wd.tech.MyApp;
import com.wd.tech.Urls;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RegisterPresenter extends BasePresenter<RegisterActivity> {

    public void getRegisterData(String nickName, String phone, String encrypt_pwd) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("nickName", nickName);
            jsonObject.put("phone", phone);
            jsonObject.put("pwd", encrypt_pwd);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());

            HashMap<String, Object> map = new HashMap<>();
            map.put("nickName", nickName);
            map.put("phone", phone);
            map.put("pwd", encrypt_pwd);
            //判断网络
            if (InternetUtil.getNetworkState(MyApp.context) != InternetUtil.NETWORN_NONE) {
                NetUtils.getNetUtils().postInfo(Urls.Register_Url, map, new NetUtils.GetJsonListener() {
                    @Override
                    public void success(String json) {
                        //解析
                        RegisterBean registerBean = new Gson().fromJson(json, RegisterBean.class);
                        if (registerBean != null) {
                            if (registerBean.getMessage().equals("0000")) {
                                Toast.makeText(MyApp.context, "注册成功！", Toast.LENGTH_SHORT).show();
                                iView.finish();
                            } else {
                                Toast.makeText(MyApp.context, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void error() {
                        Toast.makeText(MyApp.context, "注册失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(MyApp.context, "没网!玩您妈呢？", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
