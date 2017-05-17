package com.example.copengxiaolue.personaldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.copengxiaolue.personaldemo.model.GankResult;
import com.example.copengxiaolue.personaldemo.module.CategoryAdapter;
import com.example.copengxiaolue.personaldemo.net.NetWork;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private CategoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CategoryAdapter(this,null);
        mRecyclerView.setAdapter(mAdapter);

//        mImageView = (ImageView) findViewById(R.id.imageView);
//        Glide.with(this)
//                .load("http://ojyz0c8un.bkt.clouddn.com/b_2.jpg")
//                .placeholder(R.drawable.img_transition_default)
//                .error(R.drawable.img_transition_default)
//                .into(mImageView);

        NetWork.getGankApi().getGankResult("Android", 10, 1)
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
                            mAdapter.setData(data);
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

    private void initData() {

    }
}
