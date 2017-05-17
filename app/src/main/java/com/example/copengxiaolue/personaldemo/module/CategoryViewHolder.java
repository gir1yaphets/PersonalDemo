package com.example.copengxiaolue.personaldemo.module;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.copengxiaolue.personaldemo.R;

/**
 * Created by copengxiaolue on 2017/05/17.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private ImageView categoryImage;
    private TextView categoryTitle;
    private TextView categoryWho;
    private TextView categorySource;
    private TextView categoryPublish;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        categoryImage = (ImageView) itemView.findViewById(R.id.categoryImage);
        categoryTitle = (TextView) itemView.findViewById(R.id.categoryTitle);
        categoryWho = (TextView) itemView.findViewById(R.id.categoryWho);
        categorySource = (TextView) itemView.findViewById(R.id.categorySource);
        categoryPublish = (TextView) itemView.findViewById(R.id.categoryPublish);
    }

    public void setTextTitle(String text) {
        categoryTitle.setText(text);
    }

    public void setTextWho(String text) {
        categoryWho.setText(text);
    }

    public void setTextSource(String text) {
        categorySource.setText(text);
    }

    public void setTextPublish(String text) {
        categoryPublish.setText(text);
    }

    public ImageView getCategoryImage() {
        return categoryImage;
    }
}
