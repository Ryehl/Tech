package com.wd.tech.activities;

import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.beans.SignInBean;
import com.wd.tech.presenters.SignInPresenter;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends BaseActivity<SignInPresenter> {

    private List<SignInBean> list=new ArrayList<>();

    @Override
    public void initView() {
        pre.getSignInData();
    }

    @Override
    public void initData() {

    }
    public void SignInData(String json){
        //解析
        SignInBean signInBean=new Gson().fromJson(json,SignInBean.class);
        list.add(signInBean);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_sign_in;
    }

    @Override
    public SignInPresenter initPresenter() {
        return new SignInPresenter();
    }
}
