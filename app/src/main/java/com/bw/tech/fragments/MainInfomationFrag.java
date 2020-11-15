package com.bw.tech.fragments;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.MyApp;
import com.bw.tech.R;
import com.bw.tech.activities.InterestActivity;
import com.bw.tech.activities.SearchActivity;
import com.bw.tech.adapters.InformationAdapter;
import com.bw.tech.beans.InformationBean;
import com.bw.tech.beans.XBannerBean;
import com.bw.tech.presenters.XBannerPresenter;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainInfomationFrag extends BaseFragment<XBannerPresenter> {
    private XBanner xBanner;
    private List<String> list_img = new ArrayList<>();//图片数据源
    private List<String> list_title = new ArrayList<>();//标题数据源
    private XRecyclerView information_recyclerView;
    private InformationAdapter informationAdapter;
    private List<InformationBean.ResultBean> list_information = new ArrayList<>();//咨讯列表数据源
    private ImageView information_interest,information_search;
    @Override
    public void initView() {
        View view = getView();
        xBanner = view.findViewById(R.id.info_xbanner);
        information_recyclerView = view.findViewById(R.id.information_recycle);
        information_interest=view.findViewById(R.id.information_interest);
        information_search=view.findViewById(R.id.information_search);
    }

    @Override
    public void initData() {
        pre.getXBannerData();
        pre.InformationData();

        //点击跳转到分类页面
        information_interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), InterestActivity.class);
                startActivity(intent);
            }
        });

        //点击跳转到搜索页面
        information_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    /*
    轮播图
     */
    public void XBannerData(String json) {

        //解析数据
        XBannerBean xBannerBean = new Gson().fromJson(json, XBannerBean.class);
        final List<XBannerBean.ResultBean> list = xBannerBean.getResult();
//        for(int i=0;i<list.size();i++){
//            list_img.add(list);
//        }
        //TODO
        list_img.clear();
        list_title.clear();
        //加强for循环将  图片跟对应的标题全部设置上
        for (XBannerBean.ResultBean bean : list) {
            list_img.add(bean.getImageUrl());
            list_title.add(bean.getTitle());
        }
        //设置数据
        xBanner.setData(list_img, list_title);
        //用Glide设置图片
        xBanner.setmAdapter((banner, model, view, position) -> Glide.with(getContext()).load(list_img.get(position)).into((ImageView) view));
        //默认 横向移动
        xBanner.setPageTransformer(Transformer.Default);
        //时间
        xBanner.setPageChangeDuration(2000);


    }

    /*
     咨讯列表展示
     */
    public void InformationData(String json) {
        //解析
        InformationBean informationBean = new Gson().fromJson(json, InformationBean.class);
        Log.i("TAG", informationBean.toString());
        list_information.addAll(informationBean.getResult());
        //适配器
        informationAdapter=new InformationAdapter(list_information);
        //设置适配器
        information_recyclerView.setAdapter(informationAdapter);
        information_recyclerView.setLayoutManager(new LinearLayoutManager(MyApp.context));
        //头部
      //  information_recyclerView.addHeaderView(xBanner);
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
        return R.layout.frag_my_infomation;
    }

    @Override
    public XBannerPresenter initPresenter() {
        return new XBannerPresenter();
    }

}
