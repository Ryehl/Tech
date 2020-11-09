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

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            //选择
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                }
            }
            //取消选择
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            //重新选择
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initTab() {
        tab.addTab(tab.newTab().setIcon(R.drawable.ic_launcher_background).setText("消息"));
    }
}
