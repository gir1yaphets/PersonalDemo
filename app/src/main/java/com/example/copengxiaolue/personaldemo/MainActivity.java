package com.example.copengxiaolue.personaldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.copengxiaolue.personaldemo.adapter.CommonRecyclerAdapter;
import com.example.copengxiaolue.personaldemo.model.GankResult;
import com.example.copengxiaolue.personaldemo.module.CategoryHeaderFooterAdapter;
import com.example.copengxiaolue.personaldemo.module.CategoryRecyclerAdapter;
import com.example.copengxiaolue.personaldemo.net.NetWork;
import com.example.copengxiaolue.personaldemo.util.recyclerView.HeaderFooterRecyclerAdapter;
import com.example.copengxiaolue.personaldemo.util.recyclerView.RecyclerViewWrapperHeaderFooter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private RecyclerViewWrapperHeaderFooter mWrapperRecyclerView;
    private CategoryRecyclerAdapter mAdapter;
    private HeaderFooterRecyclerAdapter mHeaderFooterAdapter;

    private int mCurrentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CategoryRecyclerAdapter(this, null);
        mAdapter.setOnItemViewClickListener(new CommonRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
        mHeaderFooterAdapter = new CategoryHeaderFooterAdapter(this, null);
//        mRecyclerView.setAdapter(mHeaderFooterAdapter);


        mWrapperRecyclerView = (RecyclerViewWrapperHeaderFooter) findViewById(R.id.recyclerView);
        mWrapperRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWrapperRecyclerView.setAdapter(mHeaderFooterAdapter);
        mWrapperRecyclerView.setOnRefreshListener(new RecyclerViewWrapperHeaderFooter.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                NetWork.getGankApi().getGankResult("Android", 10, mCurrentPage)
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

//        mImageView = (ImageView) findViewById(R.id.imageView);
//        Glide.with(this)
//                .load("http://ojyz0c8un.bkt.clouddn.com/b_2.jpg")
//                .placeholder(R.drawable.img_transition_default)
//                .error(R.drawable.img_transition_default)
//                .into(mImageView);

        NetWork.getGankApi().getGankResult("Android", 10, mCurrentPage)
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
