package com.example.ole.oleandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.ole.oleandroid.R;

public class FormGuideDetails extends AppCompatActivity {
    private WebView webView = null;
//SideMenuBar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_guide_details);

//        WebView webview = new WebView(this);
//        setContentView(webview);

        Bundle bundle = getIntent().getExtras();
        String link = "https://www.premierleague.com/tables";

        if (bundle != null && bundle.getString("page").equals("specials")) {
            link = "https://www.premierleague.com/players";
            setTitle("Specials Guide");
        }

        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        //to make sure whatever link clicked is kept in the webview still
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        webView.loadUrl(link);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.webviewtoolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.forward:
                onForwardPressed();
                break;
            case R.id.refresh:
                webView.reload();
                break;
            case R.id.close:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onForwardPressed() {
        if (webView.canGoForward()) {
            webView.goForward();
        } else {
            Toast.makeText(getBaseContext(), "Can't go forward!", Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            Toast.makeText(getBaseContext(), "Can't go back!", Toast.LENGTH_LONG).show();
        }
    }
}
