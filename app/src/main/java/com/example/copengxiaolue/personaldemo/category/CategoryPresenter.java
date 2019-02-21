package com.example.copengxiaolue.personaldemo.category;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.example.copengxiaolue.personaldemo.model.GankResult;
import com.example.copengxiaolue.personaldemo.net.NetWork;
import com.example.copengxiaolue.personaldemo.util.ContextUtil;
import com.example.copengxiaolue.personaldemo.webview.WebViewActivity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by copengxiaolue on 2017/05/25.
 */

public class CategoryPresenter implements CategoryContract.ICategoryPresenter {

    private CategoryContract.ICategoryView mView;
    private int mCurrentPage = 0;

    public CategoryPresenter(CategoryContract.ICategoryView view) {
        this.mView = view;
    }

    @Override
    public void startCategoryRefresh(String category) {
                final Handler mainHandler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        mView.itemsRefreshSuccess(null);
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
    public void startCategoryLoadMore(String category) {
                NetWork.getGankApi().getGankResult(category, 10, mCurrentPage)
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
                                    mView.itemsLoadSuccess(data);
                                    mCurrentPage += 1;
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                mView.getItemsFail();
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }

    @Override
    public void openItemWebView(String url) {
        Intent intent = new Intent(ContextUtil.getContext(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.WEB_URL, url);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ContextUtil.getContext().startActivity(intent);
    }
}
