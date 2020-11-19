package com.wd.tech.fragments;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.base.BaseFragment;
import com.wd.tech.R;
import com.wd.tech.presenters.FragMsgMessagePresenter;

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

    public void setAdap(){
        recy_show.setLayoutManager(new LinearLayoutManager(getContext()));
        //recy_show.setAdapter();
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
