package com.example.copengxiaolue.personaldemo.category;

/**
 * Created by copengxiaolue on 2017/05/23.
 */

public interface CategoryContract {
    interface ICategoryPresenter {
        void startCategoryRefresh();

        void startCategoryLoadMore();
    }

    interface ICategoryView {
        void getCategoryItemsFail();

        void getCategoryItemsSuccess();
    }
}
