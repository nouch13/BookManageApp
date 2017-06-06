package com.github.nouch13.bookmanageapp

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // レイアウトで指定のWebViewIdを指定
        val myWebView: WebView = findViewById(R.id.webView1) as WebView

        // 標準ブラウザ起動させない
        myWebView.loadUrl("http://www.uec.ac.jp/")

        // JavaScriptを許可する
        myWebView.settings?.javaScriptEnabled = true

    }

}
