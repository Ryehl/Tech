package com.wd.tech.fragments;


import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.mylibrary.base.BaseFragment;
import com.wd.tech.MyApp;
import com.wd.tech.R;
import com.wd.tech.activities.DetailsActivity;
import com.wd.tech.activities.InterestActivity;
import com.wd.tech.activities.SearchActivity;
import com.wd.tech.activities.WebViewActivity;
import com.wd.tech.adapters.InformationAdapter;
import com.wd.tech.beans.AddCollectionBean;
import com.wd.tech.beans.InformationBean;
import com.wd.tech.beans.XBannerBean;
import com.wd.tech.presenters.XBannerPresenter;
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
    private int page = 1, count = 5;
    private List<String> list_img = new ArrayList<>();//图片数据源
    private List<String> list_title = new ArrayList<>();//标题数据源
    private XRecyclerView information_recyclerView;
    private InformationAdapter informationAdapter;
    private List<InformationBean.ResultBean> list_information = new ArrayList<>();//咨讯列表数据源
    private ImageView information_interest, information_search;

    @Override
    public void initView() {
        View view = getView();
        xBanner = view.findViewById(R.id.info_xbanner);
        information_recyclerView = view.findViewById(R.id.information_recycle);
        information_interest = view.findViewById(R.id.information_interest);
        information_search = view.findViewById(R.id.information_search);
    }

    @Override
    public void initData() {
        //轮播图
        pre.getXBannerData();
        //咨询
        pre.InformationData(page, count);

        //点击跳转到分类页面
        information_interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InterestActivity.class);
                startActivity(intent);
            }
        });

        //点击跳转到搜索页面
        information_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
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
        list_img.clear();
        list_title.clear();
        //加强for循环将  图片跟对应的标题全部设置上
        for (XBannerBean.ResultBean bean : list) {
            list_img.add(bean.getImageUrl());
            list_title.add(bean.getTitle());
        }


//        xBanner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //if(xBannerBean.getResult().get(0).getRank()==5){
//                    Intent intent=new Intent(getActivity(),DetailsActivity.class);
//                    startActivity(intent);
//                //}
//            }
//        });
        xBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                if(xBannerBean.getResult().get(position).getRank()==5){
                    Intent intent=new Intent(getActivity(), WebViewActivity.class);
                    startActivity(intent);
                }
            }
        });

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
        list_information = informationBean.getResult();
        //适配器
        informationAdapter = new InformationAdapter(list_information);
        //设置适配器
        information_recyclerView.setAdapter(informationAdapter);
        information_recyclerView.setLayoutManager(new LinearLayoutManager(MyApp.context));

        /**
         *设置上拉刷新下拉加载
         */
        //开启上拉加载下拉刷新
        information_recyclerView.setPullRefreshEnabled(true);
        information_recyclerView.setLoadingMoreEnabled(true);

        information_recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override//刷新
            public void onRefresh() {
                page = 1;
                informationAdapter.notifyDataSetChanged();
                pre.InformationData(page, count);
                information_recyclerView.refreshComplete();
            }

            @Override//加载
            public void onLoadMore() {
                page++;
                pre.InformationData2(page, count);
                information_recyclerView.loadMoreComplete();
            }
        });

        //头部
        //  information_recyclerView.addHeaderView(xBanner);

        //接口回调  点击事件跳转到详情页
        informationAdapter.setOnJumpDetails(new InformationAdapter.OnJumpDetails() {
            @Override
            public void jumpdetails(int index) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("id", index);
                startActivity(intent);
            }
        });

        //收藏
        informationAdapter.setOnPraise(new InformationAdapter.OnPraise() {
            @Override
            public void praise(int index) {
                pre.getPraiseData(index);
                //list_information.get(index).getCollection();
            }
        });
    }

    public void InformationData2(String json) {
        InformationBean informationBean = new Gson().fromJson(json, InformationBean.class);
  //      Log.i("TAG", informationBean.toString());
        list_information.addAll(informationBean.getResult());
        informationAdapter.notifyDataSetChanged();
    }
    /**
     * 添加到收藏
     *
     * @param json
     */
    public void PraiseData(String json) {
        //解析
        AddCollectionBean addCollectionBean = new Gson().fromJson(json, AddCollectionBean.class);
        //吐司  收藏成功  或者  已收藏不能重复收藏
        Toast.makeText(getActivity(), addCollectionBean.getMessage(), Toast.LENGTH_SHORT).show();
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
