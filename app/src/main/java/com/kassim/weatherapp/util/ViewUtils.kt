package com.kassim.weatherapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService


fun ProgressBar.show(){
    visibility = View.VISIBLE
}

fun ProgressBar.hide(){
    visibility = View.INVISIBLE
}

fun View.toast(message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


fun Context.isInternetAvailable(): Boolean {
    return if (this != null) {
        try {
            val cm =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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