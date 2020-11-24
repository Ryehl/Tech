package com.wd.tech.activities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class FlowlayoutSearch extends ViewGroup {


    public FlowlayoutSearch(Context context) {
        super(context);
    }

    public FlowlayoutSearch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowlayoutSearch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }
    /*
    测量空间本身大小 宽和高 根据子内容获取
    @param widthMeasureSpace
    @param heightMeasureSpace
    */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取父布局的模式跟尺寸
        int sizeWidth=MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth=MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight=MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight=MeasureSpec.getMode(heightMeasureSpec);

        //记录Warp_content的宽高
        int width=0;
        int height=0;

        //每一行的宽和高
        int lineWidth=0;
        int lineHeight=0;

        //获取子元素的数量
        int cCount=getChildCount();
        //进行遍历子元素
        for(int i=0;i<cCount;i++){
            //获取每一个子元素
            View child=getChildAt(i);
            //测量每一个子元素
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
            //获取每个子元素的属性
            MarginLayoutParams layoutParams= (MarginLayoutParams) child.getLayoutParams();

            //获取当前子元素的宽和高
            int childWidth = child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            int childHeight = child.getMeasuredHeight()+layoutParams.bottomMargin+layoutParams.topMargin;

            if(lineWidth+childWidth>width-getPaddingLeft()-getPaddingRight()){
                //记录一行的高  和 一行中的View


            }

        }

    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
