package com.example.mywheaterapp.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mywheaterapp.repository.api.ApiManager
import com.example.mywheaterapp.repository.model.CurrentWeather
import com.example.mywheaterapp.repository.model.WeatherItemModel
import com.example.mywheaterapp.repository.model.WeatherDaily
import com.example.mywheaterapp.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext




class WeatherRepository private constructor(private val netManager: NetManager,
                                            private val apiManager: ApiManager,
                                            private val context: Application) : BaseRepository(context){

    companion object {
        @Volatile
        private var instance: WeatherRepository? = null
        fun getInstance(
            netManager: NetManager,
            apiManager: ApiManager,
            context: Application) =
            instance ?: synchronized(this) {
                instance ?: WeatherRepository(netManager,apiManager,context).also { instance = it }
            }
    }

    /** GET FORECAST BY LOCATION**/
    private var weatherResponse: MutableLiveData<CurrentWeather> = MutableLiveData()
    suspend fun getWeatherByLocation(cityName: String) : LiveData<CurrentWeather>? {

        netManager.isConnectedToInternet?.let {
            if (it) {
                withContext(Dispatchers.IO){
                    try {
                        val response = apiManager.getForecastByLocation(cityName)

                        withContext(Dispatchers.Main) {
                            weatherResponse.setValue(response?.toCurrentWeather())
                        }
                    }
                    catch (e: Exception) {
                        withContext(Dispatchers.Main) {
                            context.toast("No city found")
                        }
                        Log.i("myTag",e.message)
                    }
                }
            } else {
                //withContext(Dispatchers.Default){
               // weatherResponse?.value = db.weatherDao().getLocalWeather() }
            }
        }
        return weatherResponse
    }

    fun getWeatherByLocationLiveData(): LiveData<CurrentWeather> {
        return weatherResponse
    }


    /** GET HOURLY FORECAST **/
    private var hourlyResponse: MutableLiveData<List<WeatherItemModel>> = MutableLiveData()
    suspend fun getHourlyForecast(cityName: String) : LiveData<List<WeatherItemModel>> ? {

           netManager.isConnectedToInternet?.let {
            if (it) {
                withContext(Dispatchers.IO){
                    try {
                        val response = apiManager.getHourlyForecast(cityName)

                        withContext(Dispatchers.Main) {
                            hourlyResponse.setValue(response?.toWeatherItemModel1())
                        }
                    }
                    catch (e: Exception) {
                        Log.i("myTag",e.message)
                    }
                }
            } else {
               //hourlyResponse = db.weatherDao().getLocalWeather()as ArrayList<WeatherLocal>
                }
            }
           return hourlyResponse
        }

    fun getHourlyForecastLiveDataLiveData(): LiveData<List<WeatherItemModel>> {
        return hourlyResponse
    }


    /** GET DAILY FORECAST **/
    private var dailyDaily: MutableLiveData<List<WeatherItemModel>> = MutableLiveData()
    suspend fun getDailyForecast(cityName: String, dayCount: Int): LiveData<List<WeatherItemModel>>?  {

        netManager.isConnectedToInternet?.let {
            if (it) {
                withContext(Dispatchers.IO){
                    try {
                        val response = apiManager.getDailyForecast(cityName,dayCount)

                        withContext(Dispatchers.Main) {
                            dailyDaily.setValue(response?.toWeatherItemModel2())
                        }
                    }
                    catch (e: Exception) {
                        Log.i("myTag",e.message)
                    }
                }
            } else {
                //dailyDaily = db.weatherDao().getLocalWeather()as ArrayList<WeatherLocal>
            }
        }
        return dailyDaily
    }

    fun getDailyForecastLiveData(): LiveData<List<WeatherItemModel>> {
        return dailyDaily
    }
}