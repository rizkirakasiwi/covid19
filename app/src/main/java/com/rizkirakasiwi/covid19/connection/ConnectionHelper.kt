package com.rizkirakasiwi.covid19.connection

import android.content.Context
import android.net.ConnectivityManager

object ConnectionHelper {
    fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}