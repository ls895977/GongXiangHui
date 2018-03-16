package com.gongxianghui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean isScroll;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // return false;//可行,不拦截事件,
        // return true;//不行,孩子无法处理事件
        //return super.onInterceptTouchEvent(ev);//不行,会有细微移动
        if (isScroll) {
            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }

    /**
     * 是否消费事件
     * 消费:事件就结束
     * 不消费:往父控件传
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //return false;// 可行,不消费,传给父控件
        //return true;// 可行,消费,拦截事件
        //super.onTouchEvent(ev); //不行,
        //虽然onInterceptTouchEvent中拦截了,
        //但是如果viewpage里面子控件不是viewgroup,还是会调用这个方法.
        if (isScroll) {
            return super.onTouchEvent(ev);
        } else {
            return true;// 可行,消费,拦截事件
        }
    }


    /**
     * 事件传递机制，拦截与分发是平行/向下的，处理是向上的。
     * 当禁止滑动时，需要事件传递到子控件，并且在实际处理时拦截掉已经被子控件处理的事件
     * <p>
     * 反之，则拦截，并交给自己的实现进行滑动处理
     */
    public void setScroll(boolean scroll) {

        isScroll = scroll;
    }
}
