package com.kassim.weatherapp.data.repositories

import com.kassim.weatherapp.data.network.WeatherApi

class WeatherRepository(private val api: WeatherApi) :
    SafeApiRequest() {
    suspend fun getWeatherByGeoPos(latLng: String) = apiRequest {
        api.getWeatherWithGeoPos(latLng)
    }

    suspend fun getCurrentWeatherCondition(cityId: Int) = apiRequest {
         api.getCurrentWeatherCondition(cityId)
    }
}