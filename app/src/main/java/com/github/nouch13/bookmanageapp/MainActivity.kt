package com.github.nouch13.bookmanageapp

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.KeyEvent
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val home_address: String = "https://www.google.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setScreenMain() // 最初に，ブラウザを起動
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setScreenMain() {
        setContentView(R.layout.activity_main)

        // ボタンの定義
        val button_tohome: Button = findViewById(R.id.button_home) as Button
        val button_camera: Button = findViewById(R.id.button_cam) as Button

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

        // ホームボタン
        button_tohome.setOnClickListener {myWebView.loadUrl(home_address)}
        // カメラ起動ボタン
        button_camera.setOnClickListener {setScreenSub()}

    }

    private fun setScreenSub(){
        val intent: Intent = Intent(application, Read_QRCode::class.java)
        startActivity(intent)
    }

}
