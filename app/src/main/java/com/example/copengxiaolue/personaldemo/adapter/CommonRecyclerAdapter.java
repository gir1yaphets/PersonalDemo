package com.example.copengxiaolue.personaldemo.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by copengxiaolue on 2017/05/18.
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonRecyclerHolder> {

    private List<T> mData;
    protected Context mContext;
    private OnItemViewClickListener mListener;

    public CommonRecyclerAdapter(Context mContext, List<T> mData) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public CommonRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(getLayoutResId(), parent, false);
        CommonRecyclerHolder holder = new CommonRecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CommonRecyclerHolder holder, final int position) {
        if (mData != null) {
            T data = mData.get(position);
            convertView(holder, data);

            if (mListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onItemClick(position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    protected abstract @LayoutRes int getLayoutResId();

    protected abstract void convertView(CommonRecyclerHolder holder, T data);

    public interface OnItemViewClickListener {
        void onItemClick(int position);
    }

    public void setOnItemViewClickListener(OnItemViewClickListener l) {
        mListener = l;
    }
}
