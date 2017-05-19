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
        REFRESHING,
        FINISH
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
    }

    public void setHeaderViewHeight(float height) {
        RelativeLayout.LayoutParams params = (LayoutParams) mRootView.getLayoutParams();
        params.height = (int) height;
        mRootView.setLayoutParams(params);
        invalidate();
    }

    private void setHeaderState(HeaderState state) {
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
            case REFRESHING:
                mRootView.setVisibility(VISIBLE);
                onRefreshingState();
                break;
            case FINISH:
                mRootView.setVisibility(VISIBLE);
                onFinishState();
                break;
            default:
                break;
        }
    }

    protected abstract int getHeaderLayoutResId();

    protected abstract void onHindState();

    protected abstract void onPullingState();

    protected abstract void onRefreshingState();

    protected abstract void onFinishState();
}
