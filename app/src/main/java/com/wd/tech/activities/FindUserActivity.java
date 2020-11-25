package com.wd.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.beans.FindUserBean;
import com.wd.tech.presenters.FindUserPresenter;

import java.util.ArrayList;
import java.util.List;

public class FindUserActivity extends BaseActivity<FindUserPresenter> {

    private SimpleDraweeView find_user_head;
    private ImageView finduser_bg;
    private TextView finduser_nickName,finduser_signature;
    private List<FindUserBean.ResultBean.CommunityUserVoBean> list=new ArrayList<>();
    @Override
    public void initView() {
        find_user_head=findViewById(R.id.find_user_head);
        finduser_bg=findViewById(R.id.user_bg);
        finduser_nickName=findViewById(R.id.find_user_nickName);
        finduser_signature=findViewById(R.id.find_user_signature);
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        int id=intent.getIntExtra("userId",0);
        pre.getFindUserData(id);

    }
    public void FindUserData(String json){
        //解析
     //   FindUserBean findUserBean=new Gson().fromJson(json,FindUserBean.class);
   //     FindUserBean.ResultBean.CommunityUserVoBean userVoBean=findUserBean.getResult();

//        list.addAll(findUserBean.getResult());
//        String nickName=findUserBean.getResult();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_find_user;
    }

    @Override
    public FindUserPresenter initPresenter() {
        return new FindUserPresenter();
    }
}
