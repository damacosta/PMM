package com.example.alejandro.finalproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ViewPageHelper extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page_helper);

        WebView webView = (WebView) this.findViewById(R.id.webview);
        webView.loadUrl("https://elpais.com/");
        webView.setWebViewClient(new WebViewClient());

    }
}
