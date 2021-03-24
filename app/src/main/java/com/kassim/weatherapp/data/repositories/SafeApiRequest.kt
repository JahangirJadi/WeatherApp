package com.kassim.weatherapp.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException

abstract class SafeApiRequest {

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

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {

//            val error = response.errorBody()?.toString()
//            val message = StringBuilder()
//            error?.let {
//                try {
//                    message.append(JSONObject(it).getString("message"))
//                } catch (e: JSONException) {
//
//                }
//                message.append("\n")
//
//            }
//            message.append("Error code: ${response.code()}")
            throw ApiException(response.errorBody().toString())
        }
    }
}

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)