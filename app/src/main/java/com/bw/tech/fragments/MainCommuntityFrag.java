package com.bw.tech.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.R;
import com.bw.tech.presenters.FragCommuntityPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainCommuntityFrag extends BaseFragment<FragCommuntityPresenter> {


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_my_communtity;
    }

    @Override
    public FragCommuntityPresenter initPresenter() {
        return null;
    }
}
