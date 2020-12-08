package com.wd.tech.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.tencent.mmkv.MMKV;
import com.wd.mylibrary.utils.NetUtils;
import com.wd.mylibrary.utils.Utils;
import com.wd.tech.R;

public class WebViewActivity extends AppCompatActivity {
    WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        web_view = findViewById(R.id.webview);

        MMKV mmkv = MMKV.defaultMMKV();
        String sessionId = mmkv.decodeString("sessionId");
        String userId = mmkv.decodeString("userId");
        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        Utils.setCjClient(web_view, url, sessionId, userId);

    }
}
