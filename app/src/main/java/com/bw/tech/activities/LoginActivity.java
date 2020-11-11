package com.bw.tech.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.mylibrary.utils.encrypt.RsaCoder;
import com.bw.tech.beans.LoginBean;
import com.bw.tech.R;
import com.bw.tech.presenters.LoginPresenter;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity<LoginPresenter> {

    private EditText login_phone,login_pwd;
    private Button login_but;
    private List<LoginBean> loginBeans=new ArrayList<>();
    private TextView go_register;
    @Override
    public void initView() {

        //获取登陆账号跟密码的ID
        login_phone=findViewById(R.id.login_phone);
        login_pwd=findViewById(R.id.login_pwd);
        login_but=findViewById(R.id.login_but);
        go_register=findViewById(R.id.go_register);

    }

    @Override
    public void initData() {

        //点击跳转到注册页面
        go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

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
                try {
                    String encrypt_pwd= RsaCoder.encryptByPublicKey(pwd);
                    pre.LoginInfo(phone,encrypt_pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
