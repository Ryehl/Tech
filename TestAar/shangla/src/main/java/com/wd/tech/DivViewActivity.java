package com.wd.tech;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DivViewActivity extends AppCompatActivity {
    private ImageView iv_detail;

    private ObservableScrollView scrollView;
    private TextView tv_titlebar;
    private RelativeLayout layout_title;
    private int mImageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_div_view);
        initView();
        //得到ImageView控件的高度
//        iv_detail.getHeight();//就是如果这个视图树没有绘制完执行该方法,那么是得不到高度

        //获取视图树的监听,我们得到视图树绘制完毕,我们再去得到控件的高度
        ViewTreeObserver viewTreeObserver = iv_detail.getViewTreeObserver();
        //使用视图观察者设置监听,以便获取所观察控件的高度
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //卸磨杀驴,回调监听后的,第一节事情就是移除监听,减少内存的消耗
                iv_detail.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //得到控件高度
                mImageHeight = iv_detail.getHeight();
            }
        });

        //使用我们的自定义ScrollView滚动的监听,滑动超过图片的高度,标题显示出来
        scrollView.setmScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChange(ObservableScrollView scrollView, int l, int t, int oldl, int oldt) {
                //对t参数进行判断,两种形态,一种消失没有 ; 随着滑动颜色越来越深
                Log.d("1801",t+"");
                if (t<0){
                    //设置标题隐藏
                    tv_titlebar.setVisibility(View.GONE);
                    //标题背景透明
                    layout_title.setBackgroundColor(Color.argb(0,0,0,0));
                }
                else if(t>0 && t < mImageHeight ){
                    //让标题显示出来
                    tv_titlebar.setVisibility(View.VISIBLE);
                    //获取ScrollView向下滑动,图片消失部分的比例
                    float scale = (float) t / mImageHeight;
                    //根据这个比例,让标题的颜色慢慢由浅到深
                    float alpha = 255 * scale;
                    //设置标题的内容及颜色
                    tv_titlebar.setText("阿巴阿巴阿巴阿巴阿巴啊阿巴");
                    tv_titlebar.setTextColor(Color.argb((int)alpha,0,0,0));
                    //设置标题布局颜色
                    layout_title.setBackgroundColor(Color.argb((int)alpha,255,255,255));
                }
            }
        });
    }


    private void initView() {
        iv_detail = (ImageView) findViewById(R.id.iv_detail);
        scrollView = (ObservableScrollView) findViewById(R.id.scrollView);
        tv_titlebar = (TextView) findViewById(R.id.tv_titlebar);
        layout_title = (RelativeLayout) findViewById(R.id.layout_title);
    }
}
