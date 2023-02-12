//package com.app.repairo.app.activity.dashboard;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//
//import com.app.repairo.app.R;
//import com.app.repairo.app.custom.ProgressBarHandler;
//
//public class Webview extends BackPressActivity {
//
//    private ProgressBarHandler progressBarHandler;
//    private WebView webview;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getLayoutInflater().inflate(R.layout.activity_webview,frameLayout);
//        progressBarHandler=new ProgressBarHandler(Webview.this);
//        webview=(WebView)findViewById(R.id.webview);
//        tvTitle.setText(getResources().getString(R.string.privacy_policy));
//        Intent i=getIntent();
//
//        webview.loadUrl("file:///android_asset/privacy_policy.html");
//
//        webview.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                progressBarHandler.show();
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                progressBarHandler.hide();
//            }
//        });
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId() == android.R.id.home) {
//            onBackPressed();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//}