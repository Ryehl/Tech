package com.wd.tech;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //ScrollView提供的有一个滑动监听方法,但是外界调用不到,所以我们还是要进行接口的暴露
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollViewListener != null) {
            mScrollViewListener.onScrollChange(this, l, t, oldl, oldt);
        }
    }


    //接口的固定格式/
    public interface ScrollViewListener {
        void onScrollChange(ObservableScrollView scrollView, int l, int t, int oldl, int oldt);
    }

    private ScrollViewListener mScrollViewListener = null;

    //提供方法,让外界可以设置监听对象
    public void setmScrollViewListener(ScrollViewListener scrollViewListener) {
        mScrollViewListener = scrollViewListener;

    }
}