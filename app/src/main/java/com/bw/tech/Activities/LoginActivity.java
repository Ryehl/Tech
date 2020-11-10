package com.bw.tech.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.mylibrary.bean.ConstantParameter;
import com.bw.mylibrary.utils.NetUtils;
import com.bw.tech.Beans.LoginBean;
import com.bw.tech.R;
import com.bw.tech.Urls;
import com.bw.tech.presenters.LoginPresenter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginActivity extends BaseActivity<LoginPresenter> {

    private EditText login_phone,login_pwd;
    private Button login_but;
    private List<LoginBean> loginBeans=new ArrayList<>();
    @Override
    public void initView() {

        //获取登陆账号跟密码的ID
        login_phone=findViewById(R.id.login_phone);
        login_pwd=findViewById(R.id.login_pwd);
        login_but=findViewById(R.id.login_but);


    }

    @Override
    public void initData() {

        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拿到输入框的账号跟密码
                String phone=login_phone.getText().toString();
                String pwd=login_pwd.getText().toString();
                //判断账号密码是否为空
                if (phone.length() == 0 || pwd.length() == 0){
                    Toast.makeText(LoginActivity.this, "请输入账号密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                pre.LoginInfo(phone,pwd);
            }
        });
    }

//    public void setLoginAdapter(String json){
//        //解析数据
//        LoginBean loginBean=new Gson().fromJson(json,LoginBean.class);
//      //  loginBeans.addAll(loginBean)
//    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }
}
