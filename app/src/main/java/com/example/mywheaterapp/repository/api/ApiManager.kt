package com.example.mywheaterapp.repository.api

import android.content.Context
import com.example.mywheaterapp.util.SingletonHolder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class ApiManager private constructor(context: Context) {

    companion object : SingletonHolder<ApiManager, Context>(::ApiManager)
    private val apiService = RetrofitClient.getClientJson(context).create(ApiService::class.java)

    suspend fun getForecastByLocation(city:String) = GlobalScope.async { apiService.getForecastByLocation(city) }.await()
    suspend fun getHourlyForecast(city:String) = GlobalScope.async { apiService.getHourlyForecast(city) }.await()
    suspend fun getDailyForecast(city:String,dayCount:Int) = GlobalScope.async { apiService.getDailyForecast(city)}.await()

}

