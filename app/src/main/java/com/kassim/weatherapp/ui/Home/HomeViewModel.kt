package com.kassim.weatherapp.ui.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kassim.weatherapp.data.models.CurrentWeather
import com.kassim.weatherapp.data.repositories.WeatherRepository
import com.kassim.weatherapp.util.Coroutines
import kotlinx.coroutines.Job

class HomeViewModel(
    private val repository: WeatherRepository
) : ViewModel() {
    private lateinit var job: Job
    private val _weatherReport = MutableLiveData<List<CurrentWeather>>()

    val weatherReport: LiveData<List<CurrentWeather>>
        get() = _weatherReport

    fun getWeatherReport() {
        job = Coroutines.ioThenMain(
            {
                repository.getCurrentWeatherCondition(260795)
            }, {
                _weatherReport.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }

}