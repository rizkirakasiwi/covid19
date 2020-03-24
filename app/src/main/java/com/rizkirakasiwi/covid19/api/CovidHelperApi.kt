package com.rizkirakasiwi.covid19.api

import android.content.Context
import com.google.gson.Gson
import com.rizkirakasiwi.covid19.data.covid.DataCovidMain
import okhttp3.OkHttpClient
import okhttp3.Request

class CovidHelperApi(private val context: Context) {
     fun getCovidData(url:String):DataCovidMain{
        val request = Request.Builder()
            .url(url)
            .build()

        val json = OkHttpClient().newCall(request).execute().body?.string()
        return Gson().fromJson(json, DataCovidMain::class.java)
    }
}