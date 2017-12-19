package com.example.bitjini.demoapp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.bitjini.demoapp.R;

public class Webview_activity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);


        webView = (WebView)findViewById(R.id.web);

        String data = getIntent().getExtras().getString("URL");

        Log.i("TAG","Recieved data is: "+data);

        webView.loadUrl(data);

    }




//    public void showToast(Context context, String message){
//        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//    }
}
