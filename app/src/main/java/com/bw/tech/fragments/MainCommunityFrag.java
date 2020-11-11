package com.bw.tech.fragments;

import android.view.View;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.R;
import com.bw.tech.presenters.FragCommunityPresenter;

/**
 * <p>项目名称:维度科技</p>
 * <p>简述:网络工具类</p>   Model
 *
 * @author Xaoyv
 * date 2020/10/14 16:01
 */
public class MainCommunityFrag extends BaseFragment<FragCommunityPresenter> {

    @Override
    public void initView() {
        View view = getView();

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.fragment_my_community;
    }

    @Override
    public FragCommunityPresenter initPresenter() {
        return new FragCommunityPresenter();
    }
}
