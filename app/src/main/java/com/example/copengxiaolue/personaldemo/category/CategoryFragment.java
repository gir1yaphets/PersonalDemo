package com.example.copengxiaolue.personaldemo.category;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
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
import com.example.copengxiaolue.personaldemo.module.CategoryRecyclerAdapter;
import com.example.copengxiaolue.personaldemo.net.NetWork;
import com.example.copengxiaolue.personaldemo.util.recyclerView.HeaderFooterRecyclerAdapter;
import com.example.copengxiaolue.personaldemo.util.recyclerView.RecyclerViewDivider;
import com.example.copengxiaolue.personaldemo.util.recyclerView.RecyclerViewWrapperHeaderFooter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by copengxiaolue on 2017/05/23.
 */

public class CategoryFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "CategoryFragment";

    private View mView;
    private static final String CATEGORY_NAME = "CATEGORY_NAME";

    private RecyclerViewWrapperHeaderFooter mWrapperRecyclerView;
    private CategoryRecyclerAdapter mAdapter;
    private HeaderFooterRecyclerAdapter mHeaderFooterAdapter;

    private String mCurrentCategory;
    private int mCurrentPage = 0;

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
        mCurrentCategory = getArguments().getString(CATEGORY_NAME);

        mWrapperRecyclerView = (RecyclerViewWrapperHeaderFooter) mView.findViewById(R.id.recyclerView);
        mAdapter = new CategoryRecyclerAdapter(getActivity(), null);
        mAdapter.setOnItemViewClickListener(new CommonRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
        mHeaderFooterAdapter = new CategoryHeaderFooterAdapter(getActivity(), null);
        mWrapperRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mWrapperRecyclerView.setAdapter(mHeaderFooterAdapter);
        mWrapperRecyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        mWrapperRecyclerView.setOnRefreshListener(new RecyclerViewWrapperHeaderFooter.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler mainHandler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        mWrapperRecyclerView.refreshFinish();
                        return false;
                    }
                });
                HandlerThread handlerThread = new HandlerThread(TAG);
                handlerThread.start();
                android.os.Handler handler = new android.os.Handler(handlerThread.getLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        mainHandler.sendEmptyMessage(0);
                    }
                });
            }

            @Override
            public void onLoadMore() {
                NetWork.getGankApi().getGankResult(mCurrentCategory, 10, mCurrentPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<GankResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull GankResult gankResult) {
                                if (!gankResult.isError()) {
                                    List<GankResult.ResultBean> data = gankResult.getResults();
                                    mHeaderFooterAdapter.addData(data);
                                    mWrapperRecyclerView.loadFinish();
                                    mCurrentPage += 1;
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

        NetWork.getGankApi().getGankResult(mCurrentCategory, 10, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GankResult gankResult) {
                        if (!gankResult.isError()) {
                            List<GankResult.ResultBean> data = gankResult.getResults();
                            mHeaderFooterAdapter.setData(data);
                            mCurrentPage += 1;
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
