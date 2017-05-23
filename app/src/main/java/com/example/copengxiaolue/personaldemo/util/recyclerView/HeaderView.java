package com.example.copengxiaolue.personaldemo.util.recyclerView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.copengxiaolue.personaldemo.R;

/**
 * Created by copengxiaolue on 2017/05/19.
 */

public abstract class HeaderView extends RelativeLayout {

    private RelativeLayout mRootView;
    private HeaderState mState;

    public enum HeaderState {
        HIND,
        PULLING,
        READY,
        REFRESHING,
    }

    public HeaderView(Context context) {
        super(context);
        init();
    }

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View.inflate(getContext(), getHeaderLayoutResId(), this);
        mRootView = (RelativeLayout) findViewById(R.id.rootHeaderView);
        setHeaderState(HeaderState.HIND);
        initView();
    }

    public void setHeaderViewHeight(float height) {
        RelativeLayout.LayoutParams params = (LayoutParams) mRootView.getLayoutParams();
        params.height = (int) height;
        mRootView.setLayoutParams(params);
        mRootView.setVisibility(VISIBLE);
        invalidate();
    }

    public void setHeaderState(HeaderState state) {
        mState = state;
        refreshView();
    }

    private void refreshView() {
        switch (mState) {
            case HIND:
                mRootView.setVisibility(GONE);
                onHindState();
                break;
            case PULLING:
                mRootView.setVisibility(VISIBLE);
                onPullingState();
                break;
            case READY:
                mRootView.setVisibility(VISIBLE);
                onReadyState();
                break;
            case REFRESHING:
                mRootView.setVisibility(VISIBLE);
                onRefreshingState();
                break;
            default:
                break;
        }
    }

    protected abstract void initView();

    protected abstract int getHeaderLayoutResId();

    protected abstract void onHindState();

    protected abstract void onPullingState();

    protected abstract void onRefreshingState();

    protected abstract void onReadyState();
}
