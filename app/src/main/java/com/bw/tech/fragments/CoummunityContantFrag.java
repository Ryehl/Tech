package com.bw.tech.fragments;

import android.view.View;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.R;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:联系人</p>
 *
 * @author Xaoyv
 * date 11/13/2020 1:41 PM
 */
public class CoummunityContantFrag extends BaseFragment {
    @Override
    public void initView() {
        View view = getView();
        //find view by id
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.frag_msg_contact;
    }

    @Override
    public Object initPresenter() {
        return null;
    }
}
