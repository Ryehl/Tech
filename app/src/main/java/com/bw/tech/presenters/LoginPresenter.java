package com.bw.tech.presenters;

import android.content.Intent;
import android.widget.Toast;

import com.bw.mylibrary.base.BasePresenter;
import com.bw.mylibrary.utils.InternetUtil;
import com.bw.mylibrary.utils.NetUtils;
import com.bw.tech.Activities.LoginActivity;
import com.bw.tech.Beans.LoginBean;
import com.bw.tech.MainActivity;
import com.bw.tech.MyApp;
import com.bw.tech.Urls;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginPresenter extends BasePresenter<LoginActivity> {

    public void LoginInfo(String phone,String encrypt_pwd){
        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("phone",phone);
            jsonObject.put("pwd",encrypt_pwd);
            //入参
            HashMap<String,Object> map=new HashMap<>();
            map.put("phone",phone);
            map.put("pwd",encrypt_pwd);
            RequestBody requestBody=RequestBody.create(MediaType.parse("application/json;charset=utf-8"),jsonObject.toString());
            //网络请求
            NetUtils.getNetUtils().postInfo(Urls.Login_Ulr, map, new NetUtils.GetJsonListener() {
                @Override
                public void success(String json) {
                    //设置适配器
//                    iView.setLoginAdapter(json);
                    LoginBean loginBean=new Gson().fromJson(json,LoginBean.class);
                    if(loginBean!=null){
                        if(InternetUtil.getNetworkState(MyApp.context)!=InternetUtil.NETWORN_NONE){
                                if(loginBean.getStatus().equals("0000")){
                                Toast.makeText(MyApp.context, "登陆成功！", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(iView, MainActivity.class);
                                iView.startActivity(intent);
                            }else{
                                Toast.makeText(iView, "登陆失败！", Toast.LENGTH_SHORT).show();
                            }
                        }else{
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
