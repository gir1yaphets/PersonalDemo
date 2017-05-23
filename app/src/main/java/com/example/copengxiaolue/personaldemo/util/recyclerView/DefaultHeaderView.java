package com.example.copengxiaolue.personaldemo.util.recyclerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.copengxiaolue.personaldemo.R;

/**
 * Created by copengxiaolue on 2017/05/19.
 */

public class DefaultHeaderView extends HeaderView {
    private TextView mHeaderText;

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
    protected void initView() {
        mHeaderText = (TextView) findViewById(R.id.header_text);
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
        mHeaderText.setText("下拉刷新");
    }

    @Override
    protected void onReadyState() {
        mHeaderText.setText("松开刷新");
    }

    @Override
    protected void onRefreshingState() {
        mHeaderText.setText("正在刷新");
    }
}
