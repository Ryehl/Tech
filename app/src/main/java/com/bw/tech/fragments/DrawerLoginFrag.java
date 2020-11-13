package com.bw.tech.fragments;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.R;
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

    }

    @Override
    public void initData() {
        //点击跳转到相应页面
        //收藏

        //关注

        //帖子

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
