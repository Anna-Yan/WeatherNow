package com.example.mywheaterapp.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mywheaterapp.repository.dao.WeatherLocalDao


@Database(
    entities = [WeatherLocal::class],
    version = 1
)
abstract class WeatherDatabase: RoomDatabase(){

    abstract fun weatherDao(): WeatherLocalDao

}