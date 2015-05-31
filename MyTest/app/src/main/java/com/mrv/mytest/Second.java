package com.mrv.mytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Mr.V on 2015/5/31.
 */
public class Second extends Activity{
    private WebView webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webView= (WebView) findViewById(R.id.id_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl("http://jandan.net/ooxx");
                return true;
            }
        });
        webView.loadUrl("http://jandan.net/ooxx");
    }
}
