package com.kassim.weatherapp.data.network

import com.kassim.weatherapp.data.models.CurrentWeather
import com.kassim.weatherapp.data.models.GeoPositionWeather
import com.kassim.weatherapp.util.AppConstants.Companion.baseUrl
import com.kassim.weatherapp.util.AppConstants.Companion.weatherApiKey
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("locations/v1/cities/geoposition/search?apikey=$weatherApiKey")
    suspend fun getWeatherWithGeoPos(@Query("q") latLng: String): Response<GeoPositionWeather>

    @GET("currentconditions/v1/{city_id}?apikey=$weatherApiKey")
    suspend fun getCurrentWeatherCondition(@Path("city_id") cityId: Int): Response<List<CurrentWeather>>


    companion object {
        operator fun invoke(): WeatherApi {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

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