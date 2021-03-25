package com.kassim.weatherapp.ui.Home

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kassim.weatherapp.data.models.CurrentWeather
import com.kassim.weatherapp.data.repositories.*
import com.kassim.weatherapp.util.Coroutines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: RemoteRepository,
    private val context: Context

) : ViewModel() {
    var listener: HomeListener? = null
    private lateinit var job: Job
    private val viewModelScopeIO = CoroutineScope(job + Dispatchers.IO)

    private val _weatherReport = MutableLiveData<List<CurrentWeather>>()

    val weatherReport: LiveData<List<CurrentWeather>>
        get() = _weatherReport



    fun getWeatherReport() {

        viewModelScopeIO.launch {
           val list = repository.getCurrentWeatherCondition(260795)

            when(list){
                is Success->{
                    _weatherReport.value = list
                }
            }


        }


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