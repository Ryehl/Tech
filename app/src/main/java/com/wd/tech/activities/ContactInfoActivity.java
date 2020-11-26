package com.wd.tech.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.R;
import com.wd.tech.adapters.ContactMyGroupAdap;
import com.wd.tech.beans.JsonFriendInfoBean;
import com.wd.tech.beans.JsonIsFriendBean;
import com.wd.tech.presenters.ActContactnInfoPresenter;

import java.util.List;

public class ContactInfoActivity extends BaseActivity<ActContactnInfoPresenter> {

    private Button btn;
    private SimpleDraweeView background;
    private SimpleDraweeView head;
    private TextView tv_name;
    private TextView tv_jf;
    private TextView tv_signature;
    private TextView tv_sex8bir;
    private TextView tv_phone;
    private TextView tv_email;
    private ImageView img_vip;

    //展示好友创建的分组
    private RecyclerView recy_show;
    private int friendUid = -1;
    private String userName = null;

    @Override
    public void initView() {
        //fid
        btn = findViewById(R.id.contactInfo_btn);
        background = findViewById(R.id.contactInfo_sdv_background);
        head = findViewById(R.id.contactInfo_sdv_hd);
        tv_name = findViewById(R.id.contactInfo_tv_name);
        tv_jf = findViewById(R.id.contactInfo_tv_redJf);
        tv_signature = findViewById(R.id.contactInfo_tv_signature);
        tv_sex8bir = findViewById(R.id.contactInfo_tv_sex8bir);
        tv_phone = findViewById(R.id.contactInfo_tv_phone);
        tv_email = findViewById(R.id.contactInfo_tv_email);
        img_vip = findViewById(R.id.contactInfo_img_vip);

        recy_show = findViewById(R.id.contactInfo_recy_show);
    }

    @Override
    public void initData() {
        //展示联系人的信息，判断是不是好友
        Intent intent = getIntent();
        friendUid = intent.getIntExtra("friendUid", -1);
        if (friendUid == -1) {
            Toast.makeText(this, "程序出现问题，重试一下", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        btn.setText("正在加载。。。");
        pre.checkIsMyFriend(friendUid);
        pre.getFriendInfoByUid(friendUid);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_contact_info;
    }

    @Override
    public ActContactnInfoPresenter initPresenter() {
        return new ActContactnInfoPresenter();
    }

    /**
     * 改变btn 的文字，分别设置点击事件
     *
     * @param json jsonBean
     */
    public void changeBtn(String json) {
        JsonIsFriendBean bean = new Gson().fromJson(json, JsonIsFriendBean.class);
        if (bean.getFlag() == 1) {
            btn.setText("发送消息");
            btn.setOnClickListener(v -> {
                //跳转到发送消息的页面
                Intent in = new Intent(this, ChatFriendActivity.class);
                in.putExtra("userName", userName);
                in.putExtra("friendUid", friendUid);
                startActivity(in);
                finish();
            });
        } else {
            btn.setText("添加好友");
            btn.setOnClickListener(v -> {
                //跳转到添加好友的页面
                Intent in = new Intent(this, AddfriendActivity.class);
                in.putExtra("friendUid", friendUid);
                startActivity(in);
                finish();
            });
        }
    }

    /**
     * 显示好友信息
     *
     * @param json jsonBean
     */
    public void setData(String json) {
        JsonFriendInfoBean friendInfoBean = new Gson().fromJson(json, JsonFriendInfoBean.class);
        if (friendInfoBean == null)
            return;
        JsonFriendInfoBean.ResultBean result = friendInfoBean.getResult();
        if (result == null)
            return;
        //vip = 1
        if (result.getWhetherVip() != 1) {
            img_vip.setVisibility(View.GONE);
        }
        background.setImageURI(result.getHeadPic());
        head.setImageURI(result.getHeadPic());
        tv_name.setText(result.getNickName());
        tv_jf.setText("(" + result.getIntegral() + "积分)");
        tv_signature.setText(result.getSignature());
        String sex = result.getSex() == 1 ? "男" : "女";
        tv_sex8bir.setText(sex + "(" + result.getBirthday() + ")");
        tv_phone.setText(result.getPhone());
        tv_email.setText(result.getEmail());

        //判断这个人有没有创建群
        List<JsonFriendInfoBean.ResultBean.MyGroupListBean> myGroupList = result.getMyGroupList();
        if (myGroupList == null || myGroupList.size() == 0) {
            recy_show.setVisibility(View.GONE);
        } else {
            //设置横向布局
            recy_show.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
            //设置适配器
            recy_show.setAdapter(new ContactMyGroupAdap(myGroupList));
        }
    }
}
