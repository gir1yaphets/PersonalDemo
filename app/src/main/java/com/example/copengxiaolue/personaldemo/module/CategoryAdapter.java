package com.example.copengxiaolue.personaldemo.module;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.copengxiaolue.personaldemo.R;
import com.example.copengxiaolue.personaldemo.model.GankResult;

import java.util.List;

/**
 * Created by copengxiaolue on 2017/05/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private Context mContext;
    private List<GankResult.ResultBean> mData;

    public CategoryAdapter(Context context, List<GankResult.ResultBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    public void setData(List<GankResult.ResultBean> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.category_item_view, parent, false);
        CategoryViewHolder viewHolder = new CategoryViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        if (mData != null) {
            holder.setTextTitle(mData.get(position).desc);
            holder.setTextWho(mData.get(position).who);
            holder.setTextSource(mData.get(position).source);
            holder.setTextPublish(mData.get(position).publishAt);
            ImageView categoryImage = holder.getCategoryImage();
        }
    }

    @Override
    public int getItemCount() {
        return mData == null? 0 : mData.size();
    }
}
