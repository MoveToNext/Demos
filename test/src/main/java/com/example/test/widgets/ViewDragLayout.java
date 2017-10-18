package com.example.test.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * <pre>
 * PackageName:  com.example.test.widgets
 * Description:
 * Created by :   Liu
 * date:         2017/10/18 下午1:33
 * </pre>
 */
public class ViewDragLayout extends LinearLayout {

    private ViewDragHelper mDragHelper;
    private View view_top;
    private View view_down;
    private int initialLeft;
    private int initialTop;

    public ViewDragLayout(Context context) {
        this(context, null);
    }

    public ViewDragLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewDragLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mDragHelper = ViewDragHelper.create(this, 1.0f, viewCallBack);
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT|ViewDragHelper.EDGE_BOTTOM);
    }


    ViewDragHelper.Callback viewCallBack = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return top;
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {

            super.onEdgeDragStarted(edgeFlags,pointerId);
            mDragHelper.captureChildView(view_top,pointerId);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (releasedChild == view_down) {
                mDragHelper.settleCapturedViewAt(initialLeft, initialTop);
                invalidate();
            }
        }


        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            super.onEdgeTouched(edgeFlags, pointerId);
        }

        @Override
        public boolean onEdgeLock(int edgeFlags) {
            return super.onEdgeLock(edgeFlags);
        }

        @Override
        public int getOrderedChildIndex(int index) {
            return super.getOrderedChildIndex(index);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return super.getViewHorizontalDragRange(child);
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            return super.getViewVerticalDragRange(child);
        }
    };


    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mDragHelper.continueSettling(true)){
            invalidate();
        }

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        i ("ViewDragLayout", "initialTop:" + initialTop);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        initialLeft = view_down.getLeft();
        initialTop = view_down.getTop();
        Log.d("ViewDragLayout-onLayout", "initialLeft:" + initialLeft);
        Log.d("ViewDragLayout-onLayout", "initialTop:" + initialTop);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view_top = getChildAt(0);
        view_down = getChildAt(1);
    }
}
