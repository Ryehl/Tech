package com.wd.tech.presenters;

import android.content.Intent;
import android.widget.Toast;

import com.wd.mylibrary.base.BasePresenter;
import com.wd.mylibrary.bean.ConstantMMkv;
import com.wd.mylibrary.utils.InternetUtil;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.tech.activities.LoginActivity;
import com.wd.tech.beans.LoginBean;
import com.wd.tech.activities.MainActivity;
import com.wd.tech.MyApp;
import com.wd.tech.Urls;
import com.google.gson.Gson;
import com.tencent.mmkv.MMKV;

import java.util.HashMap;

public class LoginPresenter extends BasePresenter<LoginActivity> {

    public void LoginInfo(String phone, String encrypt_pwd) {
        try {
            //入参
            HashMap<String, Object> map = new HashMap<>();
            map.put("phone", phone);
            map.put("pwd", encrypt_pwd);
            //网络请求
            NetUtils.getNetUtils().postInfo(Urls.Login_Ulr, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    //设置适配器
//                    iView.setLoginAdapter(json);
                    LoginBean loginBean = new Gson().fromJson(json, LoginBean.class);
                    if (loginBean != null) {
                        if (InternetUtil.getNetworkState(MyApp.context) != InternetUtil.NETWORN_NONE) {
                            if (loginBean.getStatus().equals("0000")) {
                                Toast.makeText(MyApp.context, "登陆成功！", Toast.LENGTH_SHORT).show();
                                //存储
                                MMKV mmkv = MMKV.defaultMMKV();
                                mmkv.putBoolean(ConstantMMkv.Key_IsLogin, true);
                                mmkv.putString("status", loginBean.getStatus());//状态
                                mmkv.putString("nickName", loginBean.getResult().getNickName());//昵称
                                mmkv.putString("phone", loginBean.getResult().getPhone());//手机号
                                mmkv.putInt("whetherVip", loginBean.getResult().getWhetherVip());//是否是Vip
                                mmkv.putInt("whetherFaceId", loginBean.getResult().getWhetherFaceId());//FaceId

                                //设置头参
                                NetUtils.getNetUtils().setHeader(loginBean.getResult().getSessionId(), String.valueOf(loginBean.getResult().getUserId()));
                                //登陆成功后跳转页面
                                Intent intent = new Intent(iView, MainActivity.class);
                                iView.startActivity(intent);
                            } else {
                                Toast.makeText(iView, "登陆失败！", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MyApp.context, "没网！玩您妈呢？", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void error() {
                    Toast.makeText(iView, "登陆失败！", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
