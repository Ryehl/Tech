package com.wd.tech.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.utils.encrypt.RsaCoder;
import com.wd.tech.beans.LoginBean;
import com.wd.tech.R;
import com.wd.tech.presenters.LoginPresenter;
import com.tencent.mmkv.MMKV;
import com.wd.tech.wxapi.Constants;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity<LoginPresenter> {

    private EditText login_phone, login_pwd;
    private Button login_but;
    private List<LoginBean> loginBeans = new ArrayList<>();
    private TextView go_register;
    private Button wechat_login;
    @Override
    public void initView() {

        //获取登陆账号跟密码的ID
        login_phone = findViewById(R.id.login_phone);
        login_pwd = findViewById(R.id.login_pwd);
        login_but = findViewById(R.id.login_but);
        go_register = findViewById(R.id.go_register);
        wechat_login=findViewById(R.id.wechat_login);
    }

    @Override
    public void initData() {

        //微信登录
        Constants.wx_api = WXAPIFactory.createWXAPI(LoginActivity.this, Constants.APP_ID, true);
        Constants.wx_api.registerApp(Constants.APP_ID);

        wechat_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发起登录请求
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                Constants.wx_api.sendReq(req);
                finish();
            }
        });
        //点击跳转到注册页面
        go_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        login_but.setOnClickListener(v -> {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
            //拿到输入框的账号跟密码
            String phone = login_phone.getText().toString();
            String pwd = login_pwd.getText().toString();
            //判断账号密码是否为空
            if (phone.length() == 0 || pwd.length() == 0) {
                Toast.makeText(LoginActivity.this, "请输入账号密码", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                String encrypt_pwd = RsaCoder.encryptByPublicKey(pwd);
                MMKV mmkv = MMKV.defaultMMKV();
                mmkv.putString("pwd", pwd);//密码
                pre.LoginInfo(phone, encrypt_pwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenter();
    }
}
