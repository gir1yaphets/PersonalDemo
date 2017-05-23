package com.example.copengxiaolue.personaldemo.util.recyclerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import java.io.InvalidClassException;

/**
 * Created by copengxiaolue on 2017/05/19.
 */

public class RecyclerViewWrapperHeaderFooter extends RecyclerView {
    private HeaderFooterRecyclerAdapter mAdapter;
    private OnRefreshListener mListener;

    private float mStartY;
    private static final int REFRESH_THRESHOLD = 150;

    private static final String TAG = "RecyclerViewWrapperHead";

    public RecyclerViewWrapperHeaderFooter(Context context) {
        super(context);
    }

    public RecyclerViewWrapperHeaderFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewWrapperHeaderFooter(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartY = e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int distanceY = (int) (e.getY() - mStartY);
                if (!canScrollVertically(-1)) {
                    if (distanceY > 0) {
                        doPulling(distanceY);
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                int finalDistanceY = (int) (e.getY() - mStartY);
                doPullUp(finalDistanceY);
                return true;
        }

        return super.onTouchEvent(e);
    }

    private void doPulling(int distanceY) {
        mAdapter.getHeaderView().setHeaderViewHeight(distanceY);
        Log.d(TAG, "doPulling() called with: distanceY = [" + distanceY + "]");
        if (distanceY > REFRESH_THRESHOLD) {
            mAdapter.getHeaderView().setHeaderState(HeaderView.HeaderState.READY);
        } else {
            mAdapter.getHeaderView().setHeaderState(HeaderView.HeaderState.PULLING);
        }
    }

    private void doPullUp(int distanceY) {
        if (distanceY > REFRESH_THRESHOLD) {
            int headerHeight = mAdapter.getHeaderView().getLayoutParams().height;
            Log.d(TAG, "doPullingFinish() called with: height = [" + headerHeight + "]");
            mAdapter.getHeaderView().setHeaderViewHeight(headerHeight);
            if (mListener != null) {
                mListener.onRefresh();
            }
        } else {
            mAdapter.getHeaderView().setHeaderState(HeaderView.HeaderState.HIND);
        }
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        //向上已经不能再滑动
        if (!canScrollVertically(1)) {

            mAdapter.getFooterView().setFooterState(FooterView.FootState.LOADING);
            if (mListener != null) {
                mListener.onLoadMore();
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if (adapter instanceof HeaderFooterRecyclerAdapter) {
            mAdapter = (HeaderFooterRecyclerAdapter) adapter;
        } else {
            try {
                throw new InvalidClassException("使用的adpter不是HeaderFooterRecyclerAdapter子类");
            } catch (InvalidClassException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadFinish() {
        mAdapter.getFooterView().setFooterState(FooterView.FootState.READY);
    }

    public void refreshFinish() {
        mAdapter.getHeaderView().setHeaderState(HeaderView.HeaderState.HIND);
    }

    public void setOnRefreshListener(OnRefreshListener l) {
        mListener = l;
    }

    public interface OnRefreshListener {
        void onRefresh();

        void onLoadMore();
    }
}
