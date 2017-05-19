package com.example.copengxiaolue.personaldemo.util.recyclerView;

import android.content.Context;
import android.view.ViewGroup;

import com.example.copengxiaolue.personaldemo.adapter.CommonRecyclerAdapter;
import com.example.copengxiaolue.personaldemo.adapter.CommonRecyclerHolder;

import java.util.List;

/**
 * Created by copengxiaolue on 2017/05/19.
 */

public abstract class HeaderFooterRecyclerAdapter<T> extends CommonRecyclerAdapter<T> {

    private static final int HEADER_TYPE = 0x1100;
    private static final int FOOTER_TYPE = 0x1101;
    private HeaderView mHeaderView;
    private FooterView mFooterView;

    public HeaderFooterRecyclerAdapter(Context mContext, List<T> mData) {
        super(mContext, mData);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER_TYPE;
        } else if (position == getItemCount() - 1) {
            return FOOTER_TYPE;
        } else {
            return super.getItemViewType(position);
        }
    }

    @Override
    public CommonRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER_TYPE) {
            return new HeaderViewHolder(mHeaderView == null ? new DefaultHeaderView(mContext) : mHeaderView);
        } else if (viewType == FOOTER_TYPE) {
            return new FooterViewHolder(mHeaderView == null ? new DefaultFooterView(mContext) : mFooterView);
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(CommonRecyclerHolder holder, int position) {
        if (position != 0 && position != getItemCount() - 1) {
            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + 2;
    }

    public void setHeaderView(HeaderView headerView) {
        this.mHeaderView = headerView;
    }

    public void setFooterView(FooterView footerView) {
        this.mFooterView = footerView;
    }
}
