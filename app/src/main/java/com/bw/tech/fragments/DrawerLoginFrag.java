package com.bw.tech.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.MyApp;
import com.bw.tech.R;
import com.bw.tech.activities.AttentionActivity;
import com.bw.tech.activities.CardActivity;
import com.bw.tech.activities.CollectActivity;
import com.bw.tech.activities.IntegralActivity;
import com.bw.tech.activities.NoticeActivity;
import com.bw.tech.activities.SettingActivity;
import com.bw.tech.activities.TaskActivity;
import com.facebook.drawee.view.SimpleDraweeView;

public class DrawerLoginFrag extends BaseFragment {
    private LinearLayout listed_collect,listed_attention,listed_card,listed_notice,listed_integral,listed_task,listed_setting;
    private SimpleDraweeView listed_head_img;
    private TextView listed_niakName;
    @Override
    public void initView() {

        View view=getView();
        listed_collect=view.findViewById(R.id.listed_collect);
        listed_attention=view.findViewById(R.id.listed_attention);
        listed_card=view.findViewById(R.id.listed_card);
        listed_notice=view.findViewById(R.id.listed_notice);
        listed_integral=view.findViewById(R.id.listed_integral);
        listed_task=view.findViewById(R.id.listed_task);
        listed_setting=view.findViewById(R.id.listed_setting);
        listed_niakName=view.findViewById(R.id.listed_nickName);
        listed_head_img=view.findViewById(R.id.listed_head_img);
    }

    @Override
    public void initData() {
        //点击跳转到相应页面
        //收藏
        listed_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApp.context, CollectActivity.class);
                startActivity(intent);
            }
        });
        //关注
        listed_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApp.context, AttentionActivity.class);
                startActivity(intent);
            }
        });
        //帖子
        listed_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApp.context, CardActivity.class);
                startActivity(intent);
            }
        });
        //通知
        listed_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApp.context, NoticeActivity.class);
                startActivity(intent);
            }
        });
        //积分
        listed_integral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApp.context, IntegralActivity.class);
                startActivity(intent);
            }
        });
        //任务
        listed_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApp.context, TaskActivity.class);
                startActivity(intent);
            }
        });
        //设置
        listed_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApp.context, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.drawer_login;
    }

    @Override
    public Object initPresenter() {
        return null;
    }
}
