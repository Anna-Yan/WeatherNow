package com.example.mywheaterapp.repository.api


import com.example.mywheaterapp.repository.model.AllResponse
import com.example.mywheaterapp.repository.model.WeatherFor5Days
import retrofit2.http.*


interface ApiService {

    companion object {
        const val OPEN_WEATHER_API_KEY = "ebf9934fbff274ff6d0ad6bbebef4035"
    }


    @GET("weather/")
    suspend fun getForecastByLocation(@Query("q") cityName : String) : AllResponse?

    @GET("forecast/")
    suspend fun getHourlyForecast(@Query("q") cityName : String) : WeatherFor5Days?

    @GET("forecast/")
    suspend fun getDailyForecast(@Query("q") cityName : String) : WeatherFor5Days?

}