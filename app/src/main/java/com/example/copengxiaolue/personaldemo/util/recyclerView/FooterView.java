package com.example.copengxiaolue.personaldemo.util.recyclerView;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by copengxiaolue on 2017/05/19.
 */

public abstract class FooterView extends RelativeLayout {

    private FootState mState;

    public enum FootState{
        NORMAL,
        LOADING,
        READY
    }

    public FooterView(Context context) {
        super(context);
        init();
    }

    public FooterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
//        inflater.inflate(getFooterLayoutResId(), this, false);
        View.inflate(getContext(), getFooterLayoutResId(), this);
        initView();
        setFooterState(FootState.NORMAL);
    }

    public void setFooterState(FootState state) {
        mState = state;
        refreshView();
    }

    private void refreshView() {
        switch (mState) {
            case NORMAL:
                onNormalState();
                break;
            case LOADING:
                onLoadingState();
                break;
            case READY:
                onReadyState();
                break;
            default:
                break;
        }
    }

    protected abstract void initView();

    protected abstract @LayoutRes int getFooterLayoutResId();

    protected abstract void onNormalState();

    protected abstract void onLoadingState();

    protected abstract void onReadyState();
}
