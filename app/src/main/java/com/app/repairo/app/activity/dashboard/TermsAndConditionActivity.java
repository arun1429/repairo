//package com.app.repairo.app.activity.dashboard;
//
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//
//import com.app.repairo.app.R;
//import com.app.repairo.app.custom.ProgressBarHandler;
//
//public class TermsAndConditionActivity extends BackPressActivity {
//    private ProgressBarHandler progressBarHandler;
//    private WebView webview;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getLayoutInflater().inflate(R.layout.activity_terms_and_condition,frameLayout);
//        overridePendingTransition(R.anim.left_in, R.anim.left_out);
//
//        progressBarHandler = new ProgressBarHandler(TermsAndConditionActivity.this);
//        tvTitle.setText(getResources().getString(R.string.terms_conditionText));
//        webview = (WebView) findViewById(R.id.webview);
//        webview.loadUrl("file:///android_asset/terms_condition.html");
//
//
//        webview.setWebViewClient(new WebViewClient() {
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
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.right_in, R.anim.right_out);
//    }
//}