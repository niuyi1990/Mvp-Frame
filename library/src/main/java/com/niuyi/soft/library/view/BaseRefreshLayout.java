package com.niuyi.soft.library.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * 名称: BaseRefreshLayout
 * 作者: 牛毅
 * 日期: 2018/6/7 15:15
 * 描述: 解决下拉刷新和ViewPager冲突
 */
public class BaseRefreshLayout extends SwipeRefreshLayout {

    private int scaledTouchSlop;

    private View mScrollUpView;
    private float startY;
    private float startX;
    private boolean mIsVpDragger;//标记是否是横向可滑动的View

    public BaseRefreshLayout(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BaseRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public void setUpChild(View view) {
        mScrollUpView = view;
    }

    @Override
    public boolean canChildScrollUp() {
        if (mScrollUpView != null) {
            return ViewCompat.canScrollVertically(mScrollUpView, -1);
        }
        return super.canChildScrollUp();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                startX = ev.getX();
                mIsVpDragger = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsVpDragger) {
                    return false;
                }

                float moveY = ev.getY();
                float moveX = ev.getX();
                float disX = Math.abs(moveX - startX);
                float disY = Math.abs(moveY - startY);

                //横向滑动距离大于竖向滑动，认为是横向滑动，不消费事件
                if (disX > disY && disX > scaledTouchSlop) {
                    mIsVpDragger = true;
                    return false;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsVpDragger = false;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
