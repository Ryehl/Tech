package com.wd.tech.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.adapters.SearchArticleAdapter;
import com.wd.tech.adapters.SearchAuthorAdapter;
import com.wd.tech.beans.SearchArticleBean;
import com.wd.tech.beans.SearchAuthorBean;
import com.wd.tech.presenters.SearchPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity<SearchPresenter> {

    private EditText search_keyword;
    private RecyclerView search_recyclerView;
    private TabLayout tabLayout;
    private ImageView search_search;
    private SearchArticleAdapter searchArticleAdapter;
    private SearchAuthorAdapter searchAuthorAdapter;
    private List<SearchArticleBean.ResultBean> list=new ArrayList<>();
    private List<SearchAuthorBean.ResultBean> list2=new ArrayList<>();
    @Override
    public void initView() {
        search_keyword=findViewById(R.id.search_keyword);
        tabLayout=findViewById(R.id.search_tab);
        search_recyclerView=findViewById(R.id.search_recycle);
        search_search=findViewById(R.id.search_search);
    }

    @Override
    public void initData() {
        //拿到输入框的关键字



        tabLayout.addTab(tabLayout.newTab().setText("文章"));
        tabLayout.addTab(tabLayout.newTab().setText("作者"));

       // FragmentManager fragmentManager=getSupportFragmentManager();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
              //  FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
               switch (tab.getPosition()){
                   case 0:
                       search_search.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               String keyword=search_keyword.getText().toString();
                               pre.getSearchArticleData(keyword);
                           }
                       });

                       break;
                   case 1:
                       search_search.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               String keyword=search_keyword.getText().toString();
                               pre.getSearchAuthorData(keyword);
                           }
                       });
                       break;
               }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //按文章
    public void SearchArticleData(String json){
        list.clear();
        //解析
        SearchArticleBean searchArticleBean=new Gson().fromJson(json,SearchArticleBean.class);
        list.addAll(searchArticleBean.getResult());
        searchArticleAdapter=new SearchArticleAdapter(list);
        search_recyclerView.setAdapter(searchArticleAdapter);
        search_recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
    }
    //按作者
    public void SearchAuthorData(String json){
        list2.clear();
        SearchAuthorBean searchAuthorBean=new Gson().fromJson(json,SearchAuthorBean.class);
        list2.addAll(searchAuthorBean.getResult());
        searchAuthorAdapter=new SearchAuthorAdapter(list2);
        search_recyclerView.setAdapter(searchAuthorAdapter);
        search_recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
    }
    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public SearchPresenter initPresenter() {
        return new SearchPresenter();
    }
}
