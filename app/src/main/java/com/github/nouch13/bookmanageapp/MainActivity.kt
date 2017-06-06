package com.github.nouch13.bookmanageapp

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.KeyEvent
import android.view.Menu
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {

    val home_address: String = "http://www.uec.ac.jp/"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // レイアウトで指定のWebViewIdを指定
        val myWebView: WebView = findViewById(R.id.webView1) as WebView
        // 標準ブラウザ起動させない
        myWebView.setWebViewClient(WebViewClient())
        // 最初に開くページの指定
        myWebView.loadUrl(home_address)
        // JavaScriptを許可する
        myWebView.settings?.javaScriptEnabled = true
        // ズームを有効化
        myWebView.settings?.builtInZoomControls = true
    }

}
