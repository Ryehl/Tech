package com.wd.tech.fragments;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.base.BaseFragment;
import com.wd.tech.R;
import com.wd.tech.adapters.MsgMessageRecyAdap;
import com.wd.tech.presenters.FragMsgMessagePresenter;

import java.util.List;

import cn.jpush.im.android.api.model.Conversation;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:消息展示界面</p>
 *
 * @author Xaoyv
 * date 11/13/2020 1:37 PM
 */
public class MsgMessageFrag extends BaseFragment<FragMsgMessagePresenter> {

    private RecyclerView recy_show;

    @Override
    public void initView() {
        View view = getView();
        //find view by id
        recy_show = view.findViewById(R.id.msg_recy);
    }

    @Override
    public void initData() {
        //init data
        pre.getMessageList();
    }

    public void setAdap(List<Conversation> list) {
        if (list == null || list.size() == 0)
            return;
        Log.d("TAG", "setAdap: " + list.toArray().toString());
        recy_show.setLayoutManager(new LinearLayoutManager(getContext()));
        MsgMessageRecyAdap adap = new MsgMessageRecyAdap(list);
        recy_show.setAdapter(adap);
    }

    @Override
    public int getLayout() {
        return R.layout.frag_msg_message;
    }

    @Override
    public FragMsgMessagePresenter initPresenter() {
        return new FragMsgMessagePresenter();
    }
}
