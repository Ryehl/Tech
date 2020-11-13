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

    ExpandableListView elv;
    SimpleDraweeView sdv;

    @Override
    public void initView() {
        View view = getView();
        //find view by id
        elv = view.findViewById(R.id.msg_contant_elv);
        sdv = view.findViewById(R.id.msg_contant_sdv);
    }

    @Override
    public void initData() {
        //set circle
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        sdv.setHierarchy(hierarchy);
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
