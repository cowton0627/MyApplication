package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


//import android.os.Build
//import com.example.myapplication.BuildConfig


class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
//    val webSettings = webView.settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        val webView = findViewById<WebView>(R.id.webView)
//        webView.webViewClient = WebViewClient()
//        val webViewSetting = webView.settings
//        webViewSetting.javaScriptEnabled = true
//        webView.loadUrl("https://google.com")

        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.homeAssistantWebView)
//        webView.webViewClient = WebViewClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                Toast.makeText(this@MainActivity, "An error occurred", Toast.LENGTH_SHORT).show()
                if (error != null) {
                    when (error.errorCode) {
                        ERROR_HOST_LOOKUP,
                        ERROR_CONNECT,
                        ERROR_TIMEOUT -> {
                            view?.loadUrl("file:///android_asset/error_page.html")
                            Toast.makeText(this@MainActivity, "Check your internet connection", Toast.LENGTH_LONG).show()
                        }
                        ERROR_FILE_NOT_FOUND -> {
                            Toast.makeText(this@MainActivity, "File not found", Toast.LENGTH_LONG).show()
                        }
                        // Add more error handling as needed
                        else -> {
                            Toast.makeText(this@MainActivity, "An error occurred: ${error.description}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

        // Access the settings of the webView
        val webViewSettings = webView.settings
        webViewSettings.javaScriptEnabled = true
        webViewSettings.domStorageEnabled = true

//        webView.loadUrl("https://google.com")


//        val USER_AGENT_STRING = "MyAppUserAgent"
//        webSettings.userAgentString = USER_AGENT_STRING + " ${Build.MODEL} ${BuildConfig.VERSION_NAME}"
//        val defaultUserAgentString = webSettings.userAgentString
//        webSettings.userAgentString = defaultUserAgentString + " ${Build.MODEL} ${BuildConfig.VERSION_NAME}"

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WebView.setWebContentsDebuggingEnabled(true)
//        }
    }

//    override fun onBackPressed() {
//        if (webView.canGoBack()) {
//            webView.goBack()
//        } else {
//            super.onBackPressed()
//        }
//    }
}

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val i = Intent(context, MainActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(i)
    }
}

//class BootBroadcastReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
//            //Intent.ACTION_BOOT_COMPLETED == android.intent.action.BOOT_COMPLETED
//            val intent1 = Intent(context, MainActivity::class.java)
//            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context.startActivity(intent1)
//            //執行一個Activity
//
////            val intent2 = Intent(context, MyService::class.java)
////            context.startService(intent2)
//            //執行一個Service
//        }
//    }
//}

//class BootReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context, intent: Intent) {
//        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
//            val activityIntent = Intent(context, MainActivity::class.java)
//            activityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            context.startActivity(activityIntent)
//        }
//    }
//}
