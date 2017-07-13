package com.github.nouch13.bookmanageapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.KeyEvent
import android.view.Menu
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.os.Vibrator
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    val home_address: String = "http://192.168.88.29/books/"
    val REQUSET_PERMISSION: Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // パーミッションのcheck
        when{
            Build.VERSION.SDK_INT >= 23 -> {
                check_Permission()
            }
            else -> setScreenMain() // 最初に，ブラウザを起動
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setScreenMain() {

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
        button_camera.setOnClickListener {
            setScreenSub()
            myWebView.loadUrl("http://192.168.88.29/books/add-new-book/")
        }

    }

    private fun setScreenSub(){
        val intent: Intent = Intent(application, Read_QRCode::class.java)
        val v: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        v.vibrate(200)
        startActivity(intent)

    }

    private fun check_Permission(){
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            setScreenMain() // ブラウザを起動
        }else{
            request_Permission()
        }
    }

    private fun request_Permission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)){
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf<String>(android.Manifest.permission.CAMERA), REQUSET_PERMISSION)
        }else{
            val toast: Toast = Toast.makeText(this, "Please give permission of camera.", Toast.LENGTH_SHORT)
            toast.show()
            ActivityCompat.requestPermissions(this, arrayOf<String>(android.Manifest.permission.CAMERA), REQUSET_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
            setScreenMain()
            return
        }else{
            val toast: Toast = Toast.makeText(this, "Can't do everything! You should give permission of camera!!", Toast.LENGTH_SHORT)
            toast.show()
        }
    }


}
