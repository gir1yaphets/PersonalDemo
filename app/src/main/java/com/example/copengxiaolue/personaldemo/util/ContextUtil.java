package com.example.copengxiaolue.personaldemo.util;

import android.app.Application;

/**
 * Created by copengxiaolue on 2017/05/25.
 */

public class ContextUtil extends Application {
    private static ContextUtil sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static ContextUtil getContext() {
        return sInstance;
    }
}
