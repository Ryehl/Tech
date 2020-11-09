package com.bw.tech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity  extends AppCompatActivity{
    private TabLayout tab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();


    }

    private void initTab() {
        tab.addTab(tab.newTab().setIcon(R.drawable.ic_launcher_background).setText("消息"));
    }
}
