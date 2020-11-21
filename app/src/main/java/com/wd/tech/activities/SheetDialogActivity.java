package com.wd.tech.activities;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;

public class SheetDialogActivity extends BaseActivity {
    TextView man, woman;

    @Override
    public void initView() {
        man = findViewById(R.id.man);
        woman = findViewById(R.id.woman);
    }

    @Override
    public void initData() {
        //男
        man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SheetDialogActivity.this, SettingActivity.class);
                intent.putExtra("sex", "男");
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_sheetdialog;
    }

    @Override
    public Object initPresenter() {
        return null;
    }
}
