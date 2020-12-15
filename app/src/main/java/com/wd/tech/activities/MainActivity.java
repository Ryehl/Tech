package com.wd.tech.activities;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.mylibrary.bean.ConstantMMkv;
import com.wd.tech.fragments.DrawerLoginFrag;


import com.wd.tech.fragments.DrawerUnLoginFrag;
import com.wd.tech.fragments.MainMsgFrag;
import com.wd.tech.fragments.MainInfomationFrag;
import com.wd.tech.fragments.MainCommuntityFrag;
import com.wd.tech.R;
import com.wd.tech.presenters.ActMainPresenter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.tencent.mmkv.MMKV;

import java.util.List;
import java.util.Locale;

import cn.jpush.android.service.JCommonService;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;


public class MainActivity extends BaseActivity<ActMainPresenter> {

    private final String TAG = "MainActivity";

    private TabLayout tab;
    boolean isDrawer;
    private DrawerLayout drawer;
    private long exitTime = 0;
    private int position_tab = 0;

    @Override
    public void initView() {
        tab = findViewById(R.id.tab);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void initData() {
        final FragmentManager fm = getSupportFragmentManager();
        //initTab
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //选择
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FragmentTransaction temp = fm.beginTransaction();
                position_tab = tab.getPosition();
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
                position_tab = tab.getPosition();
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
        });

        tab.addTab(tab.newTab().setIcon(R.mipmap.common_tab_information_n).setText("咨询"));
        tab.addTab(tab.newTab().setIcon(R.mipmap.common_tab_message_n).setText("消息"));
        tab.addTab(tab.newTab().setIcon(R.mipmap.common_tab_community_n).setText("社区"));

        //侧滑
        final LinearLayout center = findViewById(R.id.main_center);
        final NavigationView left = findViewById(R.id.main_left);
        drawer = findViewById(R.id.main_drawer);
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
            //进行自动登录
            pre.LoginInfo(mmkv.decodeString(ConstantMMkv.Key_Phone), mmkv.decodeString(ConstantMMkv.Key_Pwd));
        } else {
            //未登录显示的页面
            ft.replace(R.id.main_left, new DrawerUnLoginFrag());
        }
        ft.commit();

        JMessageClient.registerEventReceiver(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public ActMainPresenter initPresenter() {
        return new ActMainPresenter();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //劫持返回键 -- 返回键需要按两次
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (isDrawer) {
                drawer.closeDrawer(Gravity.LEFT);
                return false;
            }

            if (position_tab != 0) {
                tab.getTabAt(0).select();
                return false;
            }

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

    /**
     * 接收在线消息
     **/
    public void onEvent(MessageEvent event) {
        //获取事件发生的会话对象
//        Conversation conversation = event.getConversation();
//        Message newMessage = event.getMessage();//获取此次离线期间会话收到的新消息列表
//        System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到一条来自%s的在线消息。\n", conversation.getTargetId()));
        Log.d(TAG, "onEvent: " + event.toString());
    }


    /**
     * 接收离线消息。
     * 类似MessageEvent事件的接收，上层在需要的地方增加OfflineMessageEvent事件的接收
     * 即可实现离线消息的接收。
     **/
    public void onEvent(OfflineMessageEvent event) {
        //获取事件发生的会话对象
        Conversation conversation = event.getConversation();
        List<Message> newMessageList = event.getOfflineMessageList();//获取此次离线期间会话收到的新消息列表
        System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到%d条来自%s的离线消息。\n", newMessageList.size(), conversation.getTargetId()));
    }


    /**
     * 接收消息漫游事件
     * 如果在JMessageClient.init时启用了消息漫游功能，则每当一个会话的漫游消息同步完成时
     * sdk会发送此事件通知上层。
     **/
    public void onEvent(ConversationRefreshEvent event) {
        //获取事件发生的会话对象
        Conversation conversation = event.getConversation();
        //获取事件发生的原因，对于漫游完成触发的事件，此处的reason应该是
        //MSG_ROAMING_COMPLETE
        ConversationRefreshEvent.Reason reason = event.getReason();
        System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到ConversationRefreshEvent事件,待刷新的会话是%s.\n", conversation.getTargetId()));
        System.out.println("事件发生的原因 : " + reason);
    }
}
