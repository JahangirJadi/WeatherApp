package com.kassim.weatherapp.ui.Home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.kassim.weatherapp.R
import com.kassim.weatherapp.data.network.WeatherApi
import com.kassim.weatherapp.data.repositories.WeatherRepository
import com.kassim.weatherapp.databinding.HomeFragmentBinding
import com.kassim.weatherapp.util.hide
import com.kassim.weatherapp.util.show

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = WeatherApi()
        val repository = WeatherRepository(api)
        val factory = HomeViewModeFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)


        binding.progressBar.show()
        viewModel.getWeatherReport()
        viewModel.weatherReport.observe(viewLifecycleOwner, Observer { weather ->
            binding.weather = weather[0]
            binding.progressBar.hide()
        })

    }

}