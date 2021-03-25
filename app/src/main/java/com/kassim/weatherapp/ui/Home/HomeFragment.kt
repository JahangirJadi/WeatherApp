package com.kassim.weatherapp.ui.Home

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kassim.weatherapp.data.network.WeatherApi
import com.kassim.weatherapp.data.repositories.RemoteRepository
import com.kassim.weatherapp.data.repositories.WeatherRepository
import com.kassim.weatherapp.databinding.HomeFragmentBinding
import com.kassim.weatherapp.util.hide
import com.kassim.weatherapp.util.show

class HomeFragment : Fragment(), HomeListener {

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
        val repositoryy = RemoteRepository(api)
        val factory = HomeViewModeFactory(repositoryy,context!!)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        binding.progressBar.show()
        viewModel.listener = this


        if (isInternetAvailable()){
            viewModel.getWeatherReport()
            viewModel.weatherReport.observe(viewLifecycleOwner, Observer { weather ->
                binding.weather = weather[0]
                binding.progressBar.hide()
            })
        }else{
            binding.progressBar.hide()
            Toast.makeText(context, "No Internet connection", Toast.LENGTH_SHORT).show()
        }


    }

    private fun isInternetAvailable(): Boolean {
        return try {
            val cm =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting
        } catch (ex: Exception) {
            ex.printStackTrace()
            false
        }
    }

    override fun onSuccess() {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    override fun onFailure(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    }

}