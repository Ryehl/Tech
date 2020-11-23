package com.wd.tech.diyview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.wd.tech.R;

/**
 * <p>Project's name:Tech</p>
 * <p>说明:支付方式选择</p>
 *
 * @author Xaoyv
 * date 11/23/2020 11:21 AM
 */
public class DiyBottomPay extends LinearLayout {
    public DiyBottomPay(Context context) {
        super(context);
        initView(context);
    }

    public DiyBottomPay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DiyBottomPay(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        View view = View.inflate(context, R.layout.diy_pay_dialog, this);
        //find view by id
        RelativeLayout rel_byJf = view.findViewById(R.id.paydialog_rel_byJf);
        RelativeLayout rel_byHy = view.findViewById(R.id.paydialog_rel_byHy);
        //set on click listener
        rel_byJf.setOnClickListener(v -> {
            //跳转到积分兑换页面
        });
        rel_byHy.setOnClickListener(v -> {
            //跳转到会员购买页面
        });
    }
}
