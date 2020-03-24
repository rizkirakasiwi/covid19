package com.rizkirakasiwi.covid19.apiHelper

import android.content.Context
import com.google.gson.Gson
import com.rizkirakasiwi.covid19.data.covid.DataCovid
import okhttp3.OkHttpClient
import okhttp3.Request

class CovidHelperApi(private val context: Context) {
     fun getCovidData(url:String):List<DataCovid>{
        val request = Request.Builder()
            .url(url)
            .build()

        val json = OkHttpClient().newCall(request).execute().body?.string()
        return Gson().fromJson(json, Array<DataCovid>::class.java).toList()
    }
}