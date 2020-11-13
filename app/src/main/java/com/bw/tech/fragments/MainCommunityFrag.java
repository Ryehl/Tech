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
import com.bw.tech.presenters.FragCommunityPresenter;
import com.google.android.material.tabs.TabLayout;

/**
 * <p>项目名称:维度科技</p>
 * <p>简述:网络工具类</p>   Model
 *
 * @author Xaoyv
 * date 2020/10/14 16:01
 */
public class MainCommunityFrag extends BaseFragment<FragCommunityPresenter> {

    TabLayout tab;
    ViewPager vp;
    ImageView iv_add;

    @Override
    public void initView() {
        View view = getView();
        if (view == null)
            return;
        tab = view.findViewById(R.id.community_tab_top);
        vp = view.findViewById(R.id.community_vp_show);
        iv_add = view.findViewById(R.id.community_img_iv);
    }

    @Override
    public void initData() {
        //init tab
        vp.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return position == 0 ? new CommunityMessageFrag() : new CoummunityContantFrag();
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

        //onclick listener
        iv_add.setOnClickListener(v -> {
            //popupwindow
        });
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_my_community;
    }

    @Override
    public FragCommunityPresenter initPresenter() {
        return new FragCommunityPresenter();
    }
}
