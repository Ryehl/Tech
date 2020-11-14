package com.bw.tech.activities;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bw.mylibrary.base.BaseActivity;
import com.bw.mylibrary.bean.ConstantMMkv;
import com.bw.tech.fragments.DrawerLoginFrag;


import com.bw.tech.fragments.DrawerUnLogin;
import com.bw.tech.fragments.MainMsgFrag;
import com.bw.tech.fragments.MainInfomationFrag;
import com.bw.tech.fragments.MainCommuntityFrag;
import com.bw.tech.R;
import com.bw.tech.presenters.ActMainPresenter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.tencent.mmkv.MMKV;


public class MainActivity extends BaseActivity<ActMainPresenter> {

    private TabLayout tab;
    boolean isDrawer;

    @Override
    public void initView() {
        tab = findViewById(R.id.tab);
    }

    @Override
    public void initData() {
        final FragmentManager fm = getSupportFragmentManager();
        //initTab
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //选择
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction temp = fm.beginTransaction();
                switch (tab.getPosition()) {
                    case 0:
                        temp.replace(R.id.main_frame_show, new MainInfomationFrag());
                        tab.setIcon(R.mipmap.common_tab_informatiion_s);
                        // ViewUtils.setViewTransparent(MainActivity.this);  沉浸式
                        break;
                    case 1:
                        temp.replace(R.id.main_frame_show, new MainMsgFrag());
                        tab.setIcon(R.mipmap.common_tab_message_s);
                        break;
                    case 2:
                        temp.replace(R.id.main_frame_show, new MainCommuntityFrag());
                        tab.setIcon(R.mipmap.common_tab_community_s);
                        break;
                }
                //提交
                temp.commit();
            }

            //取消选择
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.mipmap.common_tab_information_n);
                        break;
                    case 1:
                        tab.setIcon(R.mipmap.common_tab_message_n);
                        break;
                    case 2:
                        tab.setIcon(R.mipmap.common_tab_community_n);
                        break;
                }
            }

            //重新选择
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        tab.addTab(tab.newTab().setIcon(R.mipmap.common_tab_information_n).setText("咨询"));
        tab.addTab(tab.newTab().setIcon(R.mipmap.common_tab_message_n).setText("消息"));
        tab.addTab(tab.newTab().setIcon(R.mipmap.common_tab_community_n).setText("社区"));

        //侧滑
        final LinearLayout center = findViewById(R.id.main_center);
        final NavigationView left = findViewById(R.id.main_left);
        DrawerLayout drawer = findViewById(R.id.main_drawer);
        center.setOnTouchListener((view, motionEvent) -> {
            if (isDrawer) {
                return left.dispatchTouchEvent(motionEvent);
            } else {
                return false;
            }
        });
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                isDrawer = true;
                //获取屏幕的宽高
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                //设置右面的布局位置 根据左面菜单的right作为右面布局的left
                // 左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布 局的right
                center.layout(left.getRight(), 0, left.getRight() + display.getWidth(), display.getHeight());
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawer = false;
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

        //根据不同的登陆状态 显示login的还是unlogin的
        FragmentTransaction ft = fm.beginTransaction();
        //获取默认区的缓存
        MMKV mmkv = MMKV.defaultMMKV();
        if (mmkv.decodeBool(ConstantMMkv.Key_IsLogin)) {
            //已经登录显示的页面
            ft.replace(R.id.main_left, new DrawerLoginFrag());
            //进行登录
            pre.LoginInfo(mmkv.decodeString(ConstantMMkv.Key_Phone), mmkv.decodeString(ConstantMMkv.Key_Pwd));
        } else {
            //未登录显示的页面
            ft.replace(R.id.main_left, new DrawerUnLogin());
        }
        ft.commit();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public ActMainPresenter initPresenter() {
        return new ActMainPresenter();
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //劫持返回键 -- 返回键需要按两次
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
                return true;
            } else {
                //finish();
                //System.exit(0);
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
