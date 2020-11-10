package com.bw.tech.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bw.tech.R;

public class LoginActivity extends AppCompatActivity {

    private EditText login_phone,login_pwd;
    private Button login_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //获取登陆账号跟密码的ID
        login_phone=findViewById(R.id.login_phone);
        login_pwd=findViewById(R.id.login_pwd);
        login_but=findViewById(R.id.login_but);

        //点击登陆
        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //拿到输入框的账号跟密码
                String phone=login_phone.getText().toString();
                String pwd=login_pwd.getText().toString();


            }
        });





    }
}
