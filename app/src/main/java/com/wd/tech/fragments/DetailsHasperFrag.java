package com.wd.tech.fragments;

import android.view.View;
import android.webkit.WebView;

import com.wd.mylibrary.base.BaseFragment;
import com.wd.mylibrary.utils.Utils;
import com.wd.tech.R;
import com.wd.tech.presenters.DetailsHasperPresenter;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:有阅读权限的详情</p>
 *
 * @author Xaoyv
 * date 11/23/2020 9:20 AM
 */
public class DetailsHasperFrag extends BaseFragment<DetailsHasperPresenter> {
    private String data;
    private WebView webView;

    public DetailsHasperFrag(String data) {
        this.data = data;
    }

    @Override
    public void initView() {
        View view = getView();
        webView = view.findViewById(R.id.details_webview);
    }

    @Override
    public void initData() {
        webView.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
        Utils.setttingWebView(webView);
    }

    @Override
    public int getLayout() {
        return R.layout.details_hasreadper;
    }

    @Override
    public DetailsHasperPresenter initPresenter() {
        return new DetailsHasperPresenter();
    }
}
