package com.wd.tech.fragments;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wd.mylibrary.base.BaseFragment;
import com.wd.tech.R;
import com.wd.tech.diyview.DiyPwCommontity;
import com.wd.tech.presenters.FragMsgPresenter;
import com.google.android.material.tabs.TabLayout;
import com.wd.tech.utils.JIMUtils;

import java.util.List;
import java.util.Locale;

import cn.jmessage.biz.httptask.task.GetEventNotificationTaskMng;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.ConversationRefreshEvent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

/**
 * <p>项目名称:维度科技</p>
 * <p>简述:消息页面</p>   Model
 *
 * @author Xaoyv
 * date 2020/10/14 16:01
 */
public class MainMsgFrag extends BaseFragment<FragMsgPresenter> {

    //views
    private TabLayout tab;
    private ViewPager vp;
    private ImageView iv_add;

    private final String TAG = "MainMsgFrag";

    //pw的状态
    private boolean isPwShow = false;
    //pw
    private DiyPwCommontity pw;

    @Override
    public void initView() {
        View view = getView();
        if (view == null)
            return;
        tab = view.findViewById(R.id.msg_tab_top);
        vp = view.findViewById(R.id.msg_vp_show);
        iv_add = view.findViewById(R.id.msg_img_iv);
    }

    @Override
    public void initData() {
        JMessageClient.registerEventReceiver(this);
        JIMUtils.getJimUtils().login();
        //init view pager
        vp.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return position == 0 ? new MsgMessageFrag() : new MsgContantFrag();
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return position == 0 ? "消息" : "联系人";
            }
        });

        //set up
        tab.setupWithViewPager(vp);

        //onclick listener show or dismiss pw
        iv_add.setOnClickListener(v -> {
            //popupwindow
            if (pw == null)
                pw = new DiyPwCommontity(getContext());
            if (isPwShow) {
                pw.dismiss();
                isPwShow = false;
            } else {
                pw.showAsDropDown(iv_add);
                isPwShow = true;
            }
        });

        tab.getTabAt(1).select();
    }

    @Override
    public int getLayout() {
        return R.layout.frag_my_msg;
    }

    @Override
    public FragMsgPresenter initPresenter() {
        return new FragMsgPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //remove pw
        if (pw != null)
            pw.dismiss();
        pw = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }

    public void onEvent(GetEventNotificationTaskMng.EventEntity event) {
        //do your own business
        Log.d(TAG, "onEvent: " + event.toString());
        Log.d(TAG, "onEvent: " + event.getConEventResponse());
        Log.d(TAG, "onEvent: " + event.getConvId());
        Log.d(TAG, "onEvent: " + event.getCallback());
    }

    public void onEventMainThread(GetEventNotificationTaskMng.EventEntity event) {
        //do your own business
        Log.d(TAG, "onEventMainThread: " + event.toString());
        Log.d(TAG, "onEvent: " + event.getConEventResponse());
        Log.d(TAG, "onEvent: " + event.getConvId());
        Log.d(TAG, "onEvent: " + event.getCallback());
    }

    /**
     * 接收在线消息
     **/
    public void onEvent(MessageEvent event) {
        //获取事件发生的会话对象
        //Conversation conversation = event.getConversation();
        Message newMessage = event.getMessage();//获取此次离线期间会话收到的新消息列表
        System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到一条来自%s的在线消息", newMessage));
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
        //获取事件发生的原因，对于漫游完成触发的事件，此处的reason应该是 MSG_ROAMING_COMPLETE
        ConversationRefreshEvent.Reason reason = event.getReason();
        System.out.println(String.format(Locale.SIMPLIFIED_CHINESE, "收到ConversationRefreshEvent事件,待刷新的会话是%s.\n", conversation.getTargetId()));
        System.out.println("事件发生的原因 : " + reason);
    }
}
