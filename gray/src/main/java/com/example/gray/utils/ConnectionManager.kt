package com.example.gray.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectionManager {
    fun internet(context: Context): Boolean {

        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network1 = manager.activeNetwork ?: return false

        val networkCapabilities = manager.getNetworkCapabilities(network1) ?: return false

        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

            else -> false
        }
    }
}
