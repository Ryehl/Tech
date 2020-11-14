package com.bw.tech.fragments;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.mylibrary.base.BaseFragment;
import com.bw.tech.R;
import com.bw.tech.diyview.DiyPwCommontity;
import com.bw.tech.presenters.FragMsgPresenter;
import com.google.android.material.tabs.TabLayout;

/**
 * <p>项目名称:维度科技</p>
 * <p>简述:消息页面</p>   Model
 *
 * @author Xaoyv
 * date 2020/10/14 16:01
 */
public class MainMsgFrag extends BaseFragment<FragMsgPresenter> {

    //views
    TabLayout tab;
    ViewPager vp;
    ImageView iv_add;

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
}
