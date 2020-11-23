package com.wd.tech.fragments;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.wd.mylibrary.base.BaseFragment;
import com.wd.tech.R;
import com.wd.tech.diyview.DiyBottomPay;
import com.wd.tech.presenters.FragDetalisNoperPresenter;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:没有阅读权限的资讯详情</p>
 *
 * @author Xaoyv
 * date 11/23/2020 8:31 AM
 */
public class DetailsNoReadPerFrag extends BaseFragment<FragDetalisNoperPresenter> {
    private TextView tv_summary;
    private TextView tv_pay;
    private String summary;
    private boolean isShow = false;

    public DetailsNoReadPerFrag(String summary) {
        this.summary = summary;
    }

    @Override
    public void initView() {
        View view = getView();
        //find view by id
        tv_summary = view.findViewById(R.id.noper_tv_summary);
        tv_pay = view.findViewById(R.id.noper_tv_to_pay);
    }

    @Override
    public void initData() {
        DiyBottomPay bottomPay = new DiyBottomPay(getContext());
        BottomSheetDialog bsd = new BottomSheetDialog(getContext());
        bsd.setCancelable(true);
        bsd.setContentView(bottomPay);
        //设置付费
        tv_pay.setOnClickListener(v -> {
            //弹出弹框
            bsd.show();
        });
    }

    @Override
    public int getLayout() {
        return R.layout.details_noreadper;
    }

    @Override
    public FragDetalisNoperPresenter initPresenter() {
        return new FragDetalisNoperPresenter();
    }
}
