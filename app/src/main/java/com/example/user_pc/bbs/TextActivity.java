package com.example.user_pc.bbs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by User-PC on 2016/9/12.
 */
public class TextActivity extends AppCompatActivity {
    WebView mWebView;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        // 設定要顯示回上一頁的按鈕
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        // 取得 Intent 附帶的資料，改成文章網址存為 url
        Bundle args = this.getIntent().getExtras();
        String url = "http://disp.cc/b/" + args.getString("bi") + "-" + args.getString("ti");
        // 取得XML中的TextView，設定文字為 url
        // 取得XML中的WebView
        mWebView = (WebView) findViewById(R.id.webview);

        // WebView的設定選項
        WebSettings webSettings = mWebView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setDomStorageEnabled(true);
        // 加這行以避免跳出APP用瀏覽器開啟
        mWebView.setWebViewClient(new WebViewClient());
        // 載入網址
        mWebView.loadUrl(url);
    }
}
