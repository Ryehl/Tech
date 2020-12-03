package com.wd.tech.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.mmkv.MMKV;
import com.wd.mylibrary.base.BaseFragment;
import com.wd.tech.R;
import com.wd.tech.activities.AttentionActivity;
import com.wd.tech.activities.CardActivity;
import com.wd.tech.activities.CollectActivity;
import com.wd.tech.activities.IntegralActivity;
import com.wd.tech.activities.NoticeActivity;
import com.wd.tech.activities.PersonalInformationActivity;
import com.wd.tech.activities.SettingActivity;
import com.wd.tech.activities.SignInActivity;
import com.wd.tech.activities.TaskActivity;
import com.facebook.drawee.view.SimpleDraweeView;

public class DrawerLoginFrag extends BaseFragment {
    private LinearLayout listed_collect, listed_attention, listed_card, listed_notice, listed_integral, listed_task, listed_setting;
    private SimpleDraweeView listed_head_img;
    private TextView listed_nickName,listed_signature;
    private ImageView sign_in;

    @Override
    public void initView() {
        View view = getView();
        listed_collect = view.findViewById(R.id.listed_collect);
        listed_attention = view.findViewById(R.id.listed_attention);
        listed_card = view.findViewById(R.id.listed_card);
        listed_notice = view.findViewById(R.id.listed_notice);
        listed_integral = view.findViewById(R.id.listed_integral);
        listed_task = view.findViewById(R.id.listed_task);
        listed_setting = view.findViewById(R.id.listed_setting);
        listed_nickName = view.findViewById(R.id.listed_nickName);
        listed_head_img = view.findViewById(R.id.listed_head_img);
        sign_in = view.findViewById(R.id.sign_in);
        listed_signature=view.findViewById(R.id.listed_signature);
    }

    @Override
    public void initData() {
        //签到
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });
        //完善个人信息
        listed_head_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonalInformationActivity.class);
                startActivity(intent);
            }
        });
        /*
        点击跳转到相应页面
         */
        //收藏
        listed_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CollectActivity.class);
                startActivity(intent);
            }
        });
        //关注
        listed_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AttentionActivity.class);
                startActivity(intent);
            }
        });
        //帖子
        listed_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CardActivity.class);
                startActivity(intent);
            }
        });
        //通知
        listed_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
            }
        });
        //积分
        listed_integral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IntegralActivity.class);
                startActivity(intent);
            }
        });
        //任务
        listed_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaskActivity.class);
                startActivity(intent);
            }
        });
        //设置
        listed_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });

        //将昵称拿出来并设置
        MMKV mmkv = MMKV.defaultMMKV();
        String nickName = mmkv.decodeString("nickName");
        String signature=mmkv.decodeString("signature");
        listed_signature.setText(signature);
        listed_nickName.setText(nickName);
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
