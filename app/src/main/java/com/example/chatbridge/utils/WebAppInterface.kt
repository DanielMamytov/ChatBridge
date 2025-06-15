package com.example.chatbridge.utils

import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast

class WebAppInterface(
    private val context: Context,
    private val webView: WebView
) {

    @JavascriptInterface
    fun sendToAI(message: String) {
        val response = "Echo: $message"

        webView.post {
            webView.evaluateJavascript("displayAIResponse('$response');", null)
        }
    }
}

