package com.kassim.weatherapp.util

import android.content.Context
import android.net.ConnectivityManager

class Utils(private val context:Context) {

    fun isInternetAvailable(): Boolean {
        return if (this != null) {
            try {
                val cm =
                    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting
            } catch (ex: Exception) {
                ex.printStackTrace()
                false
            }
        } else {
            false
        }
    }

}