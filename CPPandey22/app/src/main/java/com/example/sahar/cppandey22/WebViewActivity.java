package com.example.sahar.cppandey22;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by sahar on 22/12/17.
 */

public class WebViewActivity extends DrawerBaseActivity {
    WebView webView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.web_view);
        String url="";
        Intent intent=getIntent();
        url=intent.getStringExtra("url");
        Log.e("url",""+url);
        startWebView(url);
    }

    private void startWebView(String url){

        webView.setWebViewClient(new WebViewClient(){
            ProgressDialog progressDialog;

            public boolean shouldOverrideUrlLoading(WebView view,String url){
                view.loadUrl(url);
                return true;
            }

            public void onLoadResource (WebView view, String url) {
                if(progressDialog==null){
                    progressDialog=new ProgressDialog(WebViewActivity.this);

                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }
            @Override
            public void onPageFinished(WebView view,String url){

                try{
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                      //  progressDialog=null;
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
