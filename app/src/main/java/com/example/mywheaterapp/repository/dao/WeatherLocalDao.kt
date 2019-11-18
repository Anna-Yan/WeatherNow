package com.example.mywheaterapp.repository.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mywheaterapp.repository.WeatherLocal


@Dao
interface WeatherLocalDao {

    @Insert
    suspend fun saveWeather(weather: WeatherLocal)

    @Query("SELECT * FROM WeatherLocal")
    suspend fun getLocalWeather(): WeatherLocal


}
