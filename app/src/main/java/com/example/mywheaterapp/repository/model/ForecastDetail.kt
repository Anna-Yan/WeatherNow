package com.example.mywheaterapp.repository.model

import com.google.gson.annotations.SerializedName

data class ForecastDetail(@SerializedName("dt") var  date: Long,
                          @SerializedName("temp") var temperature : Temperature,
                          @SerializedName("weather") var description : List<WeatherDescription>,
                          @SerializedName("pressure") var pressure : Double,
                          @SerializedName("humidity") var humidity :Double)