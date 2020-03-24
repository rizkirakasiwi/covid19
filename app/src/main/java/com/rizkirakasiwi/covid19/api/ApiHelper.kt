package com.rizkirakasiwi.covid19.api

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object ApiHelper {

    @Throws(IOException::class)
    fun getJsonFromUrl(url:String):String?{
        val request = Request.Builder()
            .url(url)
            .build()

        OkHttpClient().newCall(request).execute().use { response -> return response.body?.string() }
    }

    @Throws(IOException::class)
    fun getJsonFromUrl(url:String, apiKey:String):String?{
        val request = Request.Builder()
            .header("x-api-key",apiKey)
            .url(url)
            .build()
        OkHttpClient().newCall(request).execute().use { response -> return response.body?.string() }
    }

}