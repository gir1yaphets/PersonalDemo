package com.example.copengxiaolue.personaldemo.module;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.copengxiaolue.personaldemo.R;
import com.example.copengxiaolue.personaldemo.adapter.CommonRecyclerHolder;
import com.example.copengxiaolue.personaldemo.model.GankResult;
import com.example.copengxiaolue.personaldemo.util.TimeUtil;
import com.example.copengxiaolue.personaldemo.util.recyclerView.HeaderFooterRecyclerAdapter;

import java.util.List;

/**
 * Created by copengxiaolue on 2017/05/19.
 */

public class CategoryHeaderFooterAdapter extends HeaderFooterRecyclerAdapter<GankResult.ResultBean> {
    public CategoryHeaderFooterAdapter(Context mContext, List<GankResult.ResultBean> mData) {
        super(mContext, mData);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.category_item_view;
    }

    @Override
    protected void convertView(CommonRecyclerHolder holder, GankResult.ResultBean data) {
        ImageView categoryImage = (ImageView) holder.getViewById(R.id.categoryImage);

        if (data.images != null && data.images.size() != 0) {
            Glide.with(mContext)
                    .load(data.images.get(0))
                    .placeholder(R.mipmap.image_default)
                    .error(R.mipmap.image_default)
                    .into(categoryImage);
        } else {
            Glide.with(mContext).load(R.mipmap.image_default);
        }

        holder.setText(R.id.categoryTitle, data.desc);
        holder.setText(R.id.categoryWho, data.who);
        holder.setText(R.id.categoryPublish, TimeUtil.dateFormat(data.publishedAt));
        holder.setText(R.id.categorySource, data.source);
    }
}
