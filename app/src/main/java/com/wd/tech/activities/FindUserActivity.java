package com.wd.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.utils.TypeConversionUtils;
import com.wd.tech.R;
import com.wd.tech.beans.FindUserBean;
import com.wd.tech.presenters.FindUserPresenter;

import java.util.ArrayList;
import java.util.List;

public class FindUserActivity extends BaseActivity<FindUserPresenter> {

    private SimpleDraweeView find_user_head;
    private SimpleDraweeView finduser_bg;
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
        int id=intent.getIntExtra("userId",1);
        String head=intent.getStringExtra("head");
        String signature=intent.getStringExtra("signature");
        String nickName=intent.getStringExtra("nickName");
        pre.getFindUserData(id);


        Uri uri=Uri.parse(head);
        find_user_head.setImageURI(uri);
        finduser_signature.setText(signature);

        finduser_bg.setImageURI(head);
        finduser_nickName.setText(nickName);
    }
    public void FindUserData(String json){
        //解析
        FindUserBean findUserBean=new Gson().fromJson(json,FindUserBean.class);
        //Log.i("TAG++",json);
        final List<FindUserBean.ResultBean> result_list = findUserBean.getResult();


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
