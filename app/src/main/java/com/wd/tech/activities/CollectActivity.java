package com.wd.tech.activities;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.mylibrary.base.BaseActivity;
import com.wd.tech.MyApp;
import com.wd.tech.R;
import com.wd.tech.adapters.CollectAdapter;
import com.wd.tech.beans.CollectBean;
import com.wd.tech.presenters.CollectPresenter;
import com.google.gson.Gson;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends BaseActivity<CollectPresenter> {

    private SwipeMenuRecyclerView collect_recyclerView;
    private ImageView collect_delete, collect_back;
    private List<CollectBean.ResultBean> list = new ArrayList<>();
    private CollectAdapter collectAdapter;

    //创建菜单
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
//            int width = MyApp.context.getResources().getDimensionPixelSize(height);
            int width = ViewGroup.LayoutParams.WRAP_CONTENT;
            SwipeMenuItem completeItem = new SwipeMenuItem(MyApp.context)
                    .setBackgroundColor(Color.RED)
//                    .setImage(R.mipmap.ic_launcher)
                    .setTextColor(Color.WHITE)
//                    .setBackgroundColorResource(R.color.bg_color)
                    .setText(" 删除好友 ")
                    .setTextSize(18)
                    .setWeight(width)
                    .setHeight(height);
//            SwipeMenuItem completeItem2 = new SwipeMenuItem(context)
//                    .setBackground(R.drawable.delete)
////                    .setImage(R.mipmap.ic_launcher)
//                    .setTextColor(Color.WHITE)
////                    .setBackgroundColorResource(R.color.bg_color)
//                    .setText("   删除好友   ")
//                    .setTextSize(15)
//                    .setWeight(width)
//                    .setHeight(height);
            swipeRightMenu.addMenuItem(completeItem); // 添加菜单到右侧。
//            swipeRightMenu.addMenuItem(completeItem2); // 添加菜单到右侧。

        }
    };
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge swipeMenuBridge) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            swipeMenuBridge.closeMenu();
            int direction = swipeMenuBridge.getDirection(); // 左侧还是右侧菜单。
            final int adapterPosition = swipeMenuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = swipeMenuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
            switch (menuPosition) {
                case 0:
                    //  删除
                    int infoId = list.get(adapterPosition).getInfoId();
                    pre.getDeleteCollectData(infoId);
                    list.remove(adapterPosition);

//                    if(go_details != null){
//                        go_details.delete(list.get(0).getInfoId());
//                    }
//                    //弹窗
//                    alertDialog = new AlertDialog.Builder(MyApp.context)
//                            .setTitle("删除好友")
//                            .setMessage("确定删除好友吗？")
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            })
//                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                }
//                            }).create();
//                    alertDialog.show();
                    break;
            }
        }
    }; //菜单点击监听

    @Override
    public void initView() {
        collect_recyclerView = findViewById(R.id.collect_recycle);
        collect_back = findViewById(R.id.collect_back);
    }

    @Override
    public void initData() {
        pre.getCollectData();
        //点击返回上一页
        collect_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void CollectData(String json) {

        //解析
        CollectBean collectBean = new Gson().fromJson(json, CollectBean.class);
        list=collectBean.getResult();

        //开启长按拖拽
        collect_recyclerView.setLongPressDragEnabled(true);
        //设置添加侧滑按钮
        collect_recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        //设置滑动菜单item监听
        collect_recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);


        collectAdapter = new CollectAdapter(list);
        collect_recyclerView.setAdapter(collectAdapter);
        collectAdapter.notifyDataSetChanged();
        collect_recyclerView.setLayoutManager(new LinearLayoutManager(CollectActivity.this));

        //点击删除
//        collect_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        collectAdapter.setGo_details(new CollectAdapter.Go_Details() {
            @Override
            public void details(int index) {
                //点击跳转到详情页
                Intent intent = new Intent(CollectActivity.this, DetailsActivity.class);
                intent.putExtra("id", index);
                startActivity(intent);
            }

            //左滑删除
            @Override
            public void delete(int index_id) {
//                pre.getDeleteCollectData(index_id);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_collect;
    }

    @Override
    public CollectPresenter initPresenter() {
        return new CollectPresenter();
    }


}
