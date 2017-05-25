package com.example.copengxiaolue.personaldemo.category;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.copengxiaolue.personaldemo.R;
import com.example.copengxiaolue.personaldemo.adapter.CommonRecyclerAdapter;
import com.example.copengxiaolue.personaldemo.model.GankResult;
import com.example.copengxiaolue.personaldemo.module.CategoryHeaderFooterAdapter;
import com.example.copengxiaolue.personaldemo.util.ContextUtil;
import com.example.copengxiaolue.personaldemo.util.recyclerView.RecyclerViewDivider;
import com.example.copengxiaolue.personaldemo.util.recyclerView.RecyclerViewWrapperHeaderFooter;

import java.util.List;

/**
 * Created by copengxiaolue on 2017/05/23.
 */

public class CategoryFragment extends android.support.v4.app.Fragment implements CategoryContract.ICategoryView {

    private static final String TAG = "CategoryFragment";

    private Context mContext = ContextUtil.getContext();
    private View mView;
    private static final String CATEGORY_NAME = "CATEGORY_NAME";
    private String mCurrentCategory;

    private RecyclerViewWrapperHeaderFooter mWrapperRecyclerView;
    private CategoryHeaderFooterAdapter mCategoryAdapter;

    CategoryContract.ICategoryPresenter mPresenter;

    public static CategoryFragment newInstance(String categoryName) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_NAME, categoryName);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment_view, container, false);
        mView = view;
        initView();
        return view;
    }

    private void initView() {
        mPresenter = new CategoryPresenter(this);
        mCurrentCategory = getArguments().getString(CATEGORY_NAME);

        mWrapperRecyclerView = (RecyclerViewWrapperHeaderFooter) mView.findViewById(R.id.recyclerView);
        mCategoryAdapter = new CategoryHeaderFooterAdapter(getActivity(), null);
        mCategoryAdapter.setOnItemViewClickListener(new CommonRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onItemClick(int position) {
                String url = mCategoryAdapter.getData().get(position).url;
                mPresenter.openItemWebView(url);
            }
        });

        mWrapperRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mWrapperRecyclerView.setAdapter(mCategoryAdapter);
        mWrapperRecyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        mWrapperRecyclerView.setOnRefreshListener(new RecyclerViewWrapperHeaderFooter.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.startCategoryRefresh(mCurrentCategory);
            }

            @Override
            public void onLoadMore() {
                mPresenter.startCategoryLoadMore(mCurrentCategory);
            }
        });
    }

    @Override
    public void getItemsFail() {
        mWrapperRecyclerView.loadFinish();
        Toast.makeText(mContext, mContext.getResources().getString(R.string.network_fail), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemsLoadSuccess(List<GankResult.ResultBean> data) {
        mCategoryAdapter.addData(data);
        mWrapperRecyclerView.loadFinish();
    }

    @Override
    public void itemsRefreshSuccess(List<GankResult.ResultBean> data) {
        mWrapperRecyclerView.refreshFinish();
    }
}
