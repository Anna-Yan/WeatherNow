package com.example.mywheaterapp.repository

import android.content.Context
import androidx.room.Room
import com.example.mywheaterapp.repository.api.ApiManager


abstract class BaseRepository(private val context: Context) {

    protected val db =
        Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
}