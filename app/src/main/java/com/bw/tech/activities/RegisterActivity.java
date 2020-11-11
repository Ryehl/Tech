package com.bw.tech.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.mylibrary.utils.encrypt.RsaCoder;
import com.bw.tech.R;
import com.bw.tech.presenters.RegisterPresenter;

public class RegisterActivity extends BaseActivity<RegisterPresenter> {

    private EditText register_nickName,register_phone,register_pwd;
    private Button register_but;
    private TextView go_login;
    @Override
    public void initView() {
        register_nickName=findViewById(R.id.register_nickName);
        register_phone=findViewById(R.id.register_phone);
        register_pwd=findViewById(R.id.register_pwd);
        register_but=findViewById(R.id.register_but);
        go_login=findViewById(R.id.go_login);
    }

    @Override
    public void initData() {

        //点击跳转到登陆页面
        go_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        //点击注册
        register_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拿到输入框中的数据
                String nickName=register_nickName.getText().toString();
                String phone=register_phone.getText().toString();
                String pwd=register_pwd.getText().toString();
                if(nickName.length()==0 ||phone.length()==0||pwd.length()==0 ){
                    Toast.makeText(RegisterActivity.this, "昵称、账号、密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //加密
                try {
                    String encrypt_pwd= RsaCoder.encryptByPublicKey(pwd);
                    pre.getRegisterData(nickName,phone,encrypt_pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }
}
