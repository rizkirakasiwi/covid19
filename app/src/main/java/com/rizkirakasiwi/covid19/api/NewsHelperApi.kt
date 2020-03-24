package com.rizkirakasiwi.covid19.api

import android.content.Context
import com.google.gson.Gson
import com.rizkirakasiwi.covid19.BuildConfig
import com.rizkirakasiwi.covid19.data.news.DataNews
import okhttp3.OkHttpClient
import okhttp3.Request

class NewsHelperApi(private val context: Context) {
    private val NEWS_API_KEY = BuildConfig.NEWS_API

    fun getNewsData(url:String):DataNews{
        val request = Request.Builder()
            .header("x-api-key", NEWS_API_KEY)
            .url(url)
            .build()
        val json = OkHttpClient().newCall(request).execute()
            .body?.string()
        return Gson().fromJson(json, DataNews::class.java)
    }


}