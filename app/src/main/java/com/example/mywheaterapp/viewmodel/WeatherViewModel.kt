package com.example.mywheaterapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.mywheaterapp.repository.WeatherRepository
import com.example.mywheaterapp.repository.api.ApiManager
import com.example.mywheaterapp.repository.model.CurrentWeather
import com.example.mywheaterapp.repository.model.WeatherItemModel
import com.example.mywheaterapp.repository.model.WeatherDaily
import com.example.mywheaterapp.util.NetManager
import kotlinx.coroutines.launch


class WeatherViewModel(context: Application,cityName: String): BaseViewModel() {

    private var repository: WeatherRepository? = null

    init {
       getWeatherInfo(context,cityName)
    }

     fun getWeatherInfo(context: Application,cityName: String) = launch {
         repository = WeatherRepository.getInstance(NetManager(context), ApiManager.getInstance(context),context)
         repository?.getWeatherByLocation(cityName)
         repository?.getHourlyForecast(cityName)
         repository?.getDailyForecast(cityName,5)
    }

    fun getWeatherLiveData(context: Application): LiveData<CurrentWeather>? {
        return WeatherRepository.getInstance(NetManager(context), ApiManager.getInstance(context),context)
               .getWeatherByLocationLiveData()
    }

    fun getHourlyForecastLiveData(context: Application): LiveData<List<WeatherItemModel>> {
        return WeatherRepository.getInstance(NetManager(context), ApiManager.getInstance(context),context)
            .getHourlyForecastLiveDataLiveData()
    }

    fun getDailyForecastLiveData(context: Application): LiveData<List<WeatherItemModel>> {
        return WeatherRepository.getInstance(NetManager(context), ApiManager.getInstance(context),context)
            .getDailyForecastLiveData()
    }

    fun updateWeatherBySearch(context: Application, searchText: String) = launch {
       getWeatherInfo(context,searchText)
    }
}