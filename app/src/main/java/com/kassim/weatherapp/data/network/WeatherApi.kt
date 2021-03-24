package com.kassim.weatherapp.data.network

import com.kassim.weatherapp.data.models.GeoPositionWeather
import com.kassim.weatherapp.util.AppConstants
import com.kassim.weatherapp.util.AppConstants.Companion.baseUrl
import com.kassim.weatherapp.util.AppConstants.Companion.weatherApiKey
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("cities/geoposition/search?apikey=$weatherApiKey")
    suspend fun getWeatherWithGeoPos(@Query("q") latLng: String): Response<GeoPositionWeather>


    companion object {
        operator fun invoke(): WeatherApi {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
                .create(WeatherApi::class.java)
        }
    }
}