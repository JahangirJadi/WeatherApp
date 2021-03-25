package com.kassim.weatherapp.ui.Home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kassim.weatherapp.data.repositories.RemoteRepository
import com.kassim.weatherapp.data.repositories.WeatherRepository

@Suppress("UNCHECKED_CAST")
class HomeViewModeFactory(
    private val repository: RemoteRepository,
private val context:Context
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(
            repository,context
        ) as T
    }
}