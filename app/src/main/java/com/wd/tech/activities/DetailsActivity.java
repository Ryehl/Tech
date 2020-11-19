package com.wd.tech.activities;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.presenters.DetailsPresenter;

public class DetailsActivity extends BaseActivity<DetailsPresenter> {

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    public DetailsPresenter initPresenter() {
        return new DetailsPresenter();
    }
}
