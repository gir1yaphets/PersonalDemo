package com.example.copengxiaolue.personaldemo.util.recyclerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.copengxiaolue.personaldemo.R;

/**
 * Created by copengxiaolue on 2017/05/19.
 */

public class DefaultHeaderView extends HeaderView {
    public DefaultHeaderView(Context context) {
        super(context);
    }

    public DefaultHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int getHeaderLayoutResId() {
        return R.layout.default_header_view;
    }

    @Override
    protected void onHindState() {

    }

    @Override
    protected void onPullingState() {

    }

    @Override
    protected void onRefreshingState() {

    }

    @Override
    protected void onFinishState() {

    }
}
