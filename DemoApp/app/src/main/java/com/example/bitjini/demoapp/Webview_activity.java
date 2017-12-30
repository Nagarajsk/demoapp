package com.example.bitjini.demoapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.bitjini.demoapp.R;

public class Webview_activity extends AppCompatActivity {

    private  WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        webView = (WebView)findViewById(R.id.web);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//
//
//        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//
//        //To Enable APP cache
//        webView.getSettings().setAppCacheEnabled(true);
//        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
//
//        //Enable DOM Storage
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//
//        webSettings.setUseWideViewPort(true);
//        webSettings.setSavePassword(true);
//        webSettings.setSaveFormData(true);
//        webSettings.setEnableSmoothTransition(true);

        String data = getIntent().getExtras().getString("URL");

       // Log.i("TAG","Recieved data is: "+data);

        //webView.loadUrl(data);

        startWebView(data);


        //webView.setWebViewClient(new MyWebViewClient());

    }

//    private class MyWebViewClient extends WebViewClient{
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url){
//            if ((Uri.parse(url).getHost().equals("URL")))
//            {
//                return false;
//            }else{
//                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(url));
//                startActivity(intent);
//            }
//
//            return  true;
//        }
//
//        ProgressDialog pd=null;
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon){
//            pd= new ProgressDialog(Webview_activity.this);
//            pd.setTitle("Please wait...");
//            pd.setMessage("Loading...");
//            pd.show();
//
//            super.onPageStarted(view,url,favicon);
//        }
//
//        @Override
//        public  void onPageFinished(WebView view,String url){
//            super.onPageFinished(view, url);
//            pd.dismiss();
//        }
//
//    }
//
//
//
//    @Override
//    public boolean onKeyDown(int keycode, KeyEvent event){
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            switch (keycode){
//                case KeyEvent.KEYCODE_BACK:
//                    if (webView.canGoBack()){
//                        webView.goBack();
//                    }else{
//                        finish();
//                    }
//
//                    return true;
//            }
//        }
//
//        return super.onKeyDown(keycode,event);
//    }


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
                    progressDialog.setMessage("Please Wait...\n\nPage is Loading...");
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

    // Open previous opened link from history on webview when back button pressed
    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }




}
