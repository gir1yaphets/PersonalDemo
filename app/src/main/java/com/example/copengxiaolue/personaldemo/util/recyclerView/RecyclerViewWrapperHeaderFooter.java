package com.example.copengxiaolue.personaldemo.util.recyclerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.io.InvalidClassException;

/**
 * Created by copengxiaolue on 2017/05/19.
 */

public class RecyclerViewWrapperHeaderFooter extends RecyclerView {
    private HeaderFooterRecyclerAdapter mAdapter;
    private OnRefreshListener mListener;

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

    public void setOnRefreshListener(OnRefreshListener l) {
        mListener = l;
    }

    public interface OnRefreshListener {
        void onRefresh();

        void onLoadMore();
    }
}
