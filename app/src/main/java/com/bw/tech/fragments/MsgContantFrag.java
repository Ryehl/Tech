package com.bw.tech.fragments;

import android.view.View;
import android.widget.ExpandableListView;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.R;
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
public class MsgContantFrag extends BaseFragment {

    //二级列表展示
    ExpandableListView elv;
    //设置两个图标
    SimpleDraweeView sdv_group;
    SimpleDraweeView sdv_system;

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
    }

    @Override
    public int getLayout() {
        return R.layout.frag_msg_contact;
    }

    @Override
    public Object initPresenter() {
        return null;
    }
}
