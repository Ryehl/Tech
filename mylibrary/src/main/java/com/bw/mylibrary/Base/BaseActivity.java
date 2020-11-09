package com.bw.mylibrary.Base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bw.tech.IBaseView;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    public P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        presenter=initPresenter();
        presenter.attachView(this);
        initView();
        initData();
    }

    protected abstract P initPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    //初始化布局
    abstract int getLayout();
    //初始化控件
    abstract void initView();
    //初始化数据
    abstract void initData();
}
