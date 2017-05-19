package com.example.copengxiaolue.personaldemo.util.recyclerView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.copengxiaolue.personaldemo.R;

/**
 * Created by copengxiaolue on 2017/05/19.
 */

public class DefaultFooterView extends FooterView {
    TextView mFooterText;
    ProgressBar mFooterProgress;

    public DefaultFooterView(Context context) {
        super(context);
    }

    public DefaultFooterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DefaultFooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initView() {
        mFooterText = (TextView) findViewById(R.id.footer_text);
        mFooterProgress = (ProgressBar) findViewById(R.id.footer_progress);
    }

    @Override
    protected int getFooterLayoutResId() {
        return R.layout.default_footer_view;
    }

    @Override
    protected void onNormalState() {
        mFooterText.setText("加载更多");
        mFooterProgress.setVisibility(GONE);
    }

    @Override
    protected void onLoadingState() {
        mFooterText.setText("正在加载");
        mFooterProgress.setVisibility(VISIBLE);
    }

    @Override
    protected void onReadyState() {
        mFooterText.setText("加载更多");
        mFooterProgress.setVisibility(GONE);
    }
}
