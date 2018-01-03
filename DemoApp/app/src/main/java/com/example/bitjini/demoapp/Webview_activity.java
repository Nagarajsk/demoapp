package com.example.bitjini.demoapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview_activity extends Navigation_Drawer {

    private  WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (WebView)findViewById(R.id.web);

        String data = getIntent().getExtras().getString("URL");

       // Log.i("TAG","Recieved data is: "+data);

        startWebView(data);
    }

    private void startWebView(String url) {

        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        webView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {

                    progressDialog = new ProgressDialog(Webview_activity.this,R.style.MyAlertDialogStyle);
                    progressDialog.setMessage("Please Wait...");
                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();

                super.onPageFinished(view, url);

            }

        });

        // Javascript inabled on webview
        webView.getSettings().setJavaScriptEnabled(true);

        // Other webview options
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);

        //Load url in webview
        webView.loadUrl(url);


    }

     //Open previous opened link from history on webview when back button pressed
    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        // Let the system handle the back button
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }

}
