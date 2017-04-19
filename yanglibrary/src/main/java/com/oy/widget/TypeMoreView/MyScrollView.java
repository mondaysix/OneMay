package com.oy.widget.TypeMoreView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/10/24.
 */
public class MyScrollView extends ScrollView {
    private int downX;
    private int downY;
    private int mTouchSlop;

    public MyScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
//    public GestureDetector gestureDetector;
//    View.OnTouchListener onTouchListener;
//    public MyScrollView(Context context) {
//        this(context, null);
//    }
//
//    public MyScrollView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        // 添加了一个手势选择器
//        gestureDetector = new GestureDetector(new Yscroll());
//        setFadingEdgeLength(0);
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        // TODO 自动生成的方法存根
//        int expandSpec = MeasureSpec.makeMeasureSpec(
//                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }
//
//    @Override
//    public boolean onInterceptHoverEvent(MotionEvent event) {
//        return super.onInterceptHoverEvent(event) && gestureDetector.onTouchEvent(event);
//    }
//
//    public class Yscroll extends GestureDetector.SimpleOnGestureListener {
//
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            //控制手指滑动的距离
//            if (Math.abs(distanceY) >= Math.abs(distanceX)) {
//                return true;
//            }
//            return false;
//        }
//
//
//    }

}