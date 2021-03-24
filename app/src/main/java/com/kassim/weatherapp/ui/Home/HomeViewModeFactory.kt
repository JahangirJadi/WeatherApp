package com.kassim.weatherapp.ui.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kassim.weatherapp.data.repositories.WeatherRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModeFactory(
    private val repository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
            repository
        ) as T
    }
}