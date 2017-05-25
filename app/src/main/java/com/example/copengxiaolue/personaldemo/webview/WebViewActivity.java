package com.example.copengxiaolue.personaldemo.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.copengxiaolue.personaldemo.R;

/**
 * Created by copengxiaolue on 2017/05/25.
 */

public class WebViewActivity extends Activity {
    public static final String WEB_URL = "WEB_URL";
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String mTargetUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initWebView();
    }

    private void initWebView() {
        mWebView = (WebView) findViewById(R.id.webView);
        mProgressBar = (ProgressBar) findViewById(R.id.web_progress);
        mTargetUrl = getIntent().getStringExtra(WEB_URL);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.loadUrl(mTargetUrl);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSupportZoom(true);
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.GONE);
            mWebView.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(mTargetUrl);
            return true;
        }
    }
}
