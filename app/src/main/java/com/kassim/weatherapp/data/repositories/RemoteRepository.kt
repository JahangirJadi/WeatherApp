package com.kassim.weatherapp.data.repositories

import com.kassim.weatherapp.data.models.CurrentWeather
import com.kassim.weatherapp.data.network.WeatherApi
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlinx.coroutines.withContext as withContext1

sealed class Result<out T : List<Any>>
class Success<out T : List<Any>>(val data: T) : Result<T>()
class Error(private val exception: Throwable, val message: String = exception.localizedMessage) :
    Result<Nothing>()

class RemoteRepository(
    private val api: WeatherApi
) {
    suspend fun getCurrentWeatherCondition(cityId: Int): Result<List<Any>> {
        return withContext1(Dispatchers.IO)  {
            try {
                val call =  api.getCurrentWeatherCondition(cityId)
                Success(call)
            } catch (e: HttpException) {
                Error(e, "")
            } catch (e: IOException) {
                Error(e, "")
            } catch (e: Throwable) {
                Error(e, "")
            }

        }
    }
}

