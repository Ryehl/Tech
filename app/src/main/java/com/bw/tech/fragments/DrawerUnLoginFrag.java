package com.bw.tech.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.MyApp;
import com.bw.tech.R;
import com.bw.tech.activities.LoginActivity;

public class DrawerUnLoginFrag extends BaseFragment {
    private LinearLayout log_out_layout;
    @Override
    public void initView() {
        View view=getView();
        log_out_layout=view.findViewById(R.id.log_out_layout);
    }

    @Override
    public void initData() {
        //点击跳转到登录页面
        log_out_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.drawer_unlogin;
    }

    @Override
    public Object initPresenter() {
        return null;
    }
}
