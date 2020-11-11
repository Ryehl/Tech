package com.bw.tech.fragments;


import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.MyApp;
import com.bw.tech.R;
import com.bw.tech.beans.InformationBean;
import com.bw.tech.beans.XBannerBean;
import com.bw.tech.presenters.XBannerPresenter;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyInfomationFrag extends BaseFragment<XBannerPresenter> {
    private XBanner xBanner;
    private List<String> list_img=new ArrayList<>();//图片数据源
    private List<String> list_title=new ArrayList<>();//标题数据源

    private List<InformationBean.ResultBean> list_information=new ArrayList<>();//咨讯列表数据源
    @Override
    public void initView() {
        View view=getView();
        xBanner=view.findViewById(R.id.info_xbanner);

    }

    @Override
    public void initData() {
         pre.getXBannerData();
    }

    /*
    轮播图
     */
    public void XBannerData(String json){

        //解析数据
        XBannerBean xBannerBean=new Gson().fromJson(json,XBannerBean.class);
        final List<XBannerBean.ResultBean> list=xBannerBean.getResult();
//        for(int i=0;i<list.size();i++){
//            list_img.add(list);
//        }
        //TODO
        list_img.clear();
        list_title.clear();
        //加强for循环将  图片跟对应的标题全部设置上
        for (XBannerBean.ResultBean bean:list) {
            list_img.add(bean.getImageUrl());
            list_title.add(bean.getTitle());
        }
        //设置数据
        xBanner.setData(list_img, list_title);
        //用Glide设置图片
        xBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getContext()).load(list_img.get(position)).into((ImageView) view);
            }
        });
        //默认 横向移动
        xBanner.setPageTransformer(Transformer.Default);
        //时间
        xBanner.setPageChangeDuration(2000);

    }
    /*
     咨讯列表展示
     */
    public void InformationData(String json){
        //解析
        InformationBean informationBean=new Gson().fromJson(json,InformationBean.class);
        list_information.addAll(informationBean.getResult());

    }


    @Override
    public void onResume() {
        super.onResume();
        xBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        xBanner.stopAutoPlay();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_my_infomation;
    }

    @Override
    public XBannerPresenter initPresenter() {
        return new XBannerPresenter();
    }

}
