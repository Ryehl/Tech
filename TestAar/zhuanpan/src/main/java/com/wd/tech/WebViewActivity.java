package com.wd.tech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView web_view;
 //   MMKV mmkv=MMKV.defaultMMKV();
    String sessionId="16067828318081721";
    String userId="1721";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
//        int userId=mmkv.decodeInt("userId");
//        String sessionId=mmkv.decodeString("sessionId");

        String userInfo = sessionId + ";" + userId+"";
        WebSettings webSettings = web_view.getSettings();
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置缓存路径
//        webSettings.setAppCachePath("");
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(12);
        web_view.loadUrl("http://mobile.bwstudent.com/htm/lottery/index.html");
        String userAgentString = webSettings.getUserAgentString();
        String ua = userAgentString + "/" + userInfo;
        String[] split = ua.split("/");
        String[] split1 = split[split.length - 1].split(";");

        webSettings.setUserAgentString(userAgentString + "/" + userInfo);
    }

    private void initView() {
        web_view = (WebView) findViewById(R.id.web_view);
    }
}