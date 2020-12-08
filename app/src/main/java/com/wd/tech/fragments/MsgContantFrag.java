package com.wd.tech.fragments;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.wd.mylibrary.base.BaseFragment;
import com.wd.tech.R;
import com.wd.tech.activities.FriendRequestNoticeActivity;
import com.wd.tech.adapters.ContactExpandableListAdap;
import com.wd.tech.beans.JsonFriendListBean;
import com.wd.tech.presenters.FragMsgContantPresenter;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:联系人</p>
 *
 * @author Xaoyv
 * date 11/13/2020 1:41 PM
 */
public class MsgContantFrag extends BaseFragment<FragMsgContantPresenter> {

    //二级列表展示
    private ExpandableListView elv;
    //设置两个图标
    private SimpleDraweeView sdv_group;
    private SimpleDraweeView sdv_system;

    @Override
    public void initView() {
        View view = getView();
        if (view == null)
            return;
        //find view by id
        elv = view.findViewById(R.id.msg_contant_elv);
        sdv_group = view.findViewById(R.id.msg_contant_sdv_group);
        sdv_system = view.findViewById(R.id.msg_contant_sdv_systemMsg);
    }

    @Override
    public void initData() {
        //set circle
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        sdv_group.setHierarchy(hierarchy);
        sdv_group.setImageResource(R.mipmap.nav_btn_setting_n);
        sdv_system.setHierarchy(hierarchy);
        sdv_system.setImageResource(R.drawable.system_msg);

        //点击跳转
        sdv_system.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FriendRequestNoticeActivity.class);
            startActivity(intent);
        });

        //get data
        pre.initFriendList();
    }

    /**
     * 设置二级联系人列表的适配器
     */
    public void setElvAdap(JsonFriendListBean friendListBean) {
        if (friendListBean == null)
            return;
        if (friendListBean.getResult() == null) {
            Toast.makeText(getContext(), friendListBean.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        elv.setAdapter(new ContactExpandableListAdap(friendListBean, getContext()));
    }

    @Override
    public int getLayout() {
        return R.layout.frag_msg_contact;
    }

    @Override
    public FragMsgContantPresenter initPresenter() {
        return new FragMsgContantPresenter();
    }
}
