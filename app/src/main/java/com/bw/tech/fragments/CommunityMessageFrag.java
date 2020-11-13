package com.bw.tech.fragments;

import android.view.View;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.R;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:消息展示界面</p>
 *
 * @author Xaoyv
 * date 11/13/2020 1:37 PM
 */
public class CommunityMessageFrag extends BaseFragment {
    @Override
    public void initView() {
        View view = getView();
        //find view by id
    }

    @Override
    public void initData() {
        //init data
    }

    @Override
    public int getLayout() {
        return R.layout.frag_comminity_message;
    }

    @Override
    public Object initPresenter() {
        return null;
    }
}
