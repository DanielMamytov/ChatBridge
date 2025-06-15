package com.example.chatbridge.utils

import android.content.Context
import android.webkit.CookieManager

object CookieHelper {
    private const val PREF_NAME = "cookies_pref"
    private const val COOKIE_KEY = "cookie_string"

    fun saveCookies(context: Context, url: String) {
        val cookies = CookieManager.getInstance().getCookie(url)
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(COOKIE_KEY, cookies).apply()
    }

    fun loadCookies(context: Context, url: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val cookies = prefs.getString(COOKIE_KEY, null)
        if (!cookies.isNullOrEmpty()) {
            val cookieManager = CookieManager.getInstance()
            cookies.split(";").forEach { cookie ->
                cookieManager.setCookie(url, cookie.trim())
            }
            CookieManager.getInstance().flush()
        }
    }
    fun setCookies(url: String, cookies: Map<String, String>) {
        val cookieManager = CookieManager.getInstance()
        cookies.forEach { (key, value) ->
            cookieManager.setCookie(url, "$key=$value")
        }
        CookieManager.getInstance().flush()
    }

    fun getCookies(url: String): String? {
        return CookieManager.getInstance().getCookie(url)
    }

    fun clearCookies() {
        val cookieManager = CookieManager.getInstance()
        cookieManager.removeAllCookies(null)
        cookieManager.flush()
    }
}
