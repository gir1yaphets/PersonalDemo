package com.example.copengxiaolue.personaldemo.category;

import com.example.copengxiaolue.personaldemo.model.GankResult;

import java.util.List;

/**
 * Created by copengxiaolue on 2017/05/23.
 */

public interface CategoryContract {
    interface ICategoryPresenter {
        void startCategoryRefresh(String category);

        void startCategoryLoadMore(String category);

        void openItemWebView(String url);
    }

    interface ICategoryView {
        void getItemsFail();

        void itemsLoadSuccess(List<GankResult.ResultBean> data);

        void itemsRefreshSuccess(List<GankResult.ResultBean> data);
    }
}
