package com.bw.mylibrary.Base;

import com.bw.mylibrary.Model.IBaseView;

public class BasePresenter <V extends IBaseView>{
    public V mView;
    //绑定
    public void attachView(V view){
        mView=view;
    }
    //解绑
    public void detachView(){
        mView=null;
    }
}
