package com.example.mywheaterapp.repository.model

import com.google.gson.annotations.SerializedName

data class WeatherDaily (@SerializedName("city") var city : City,
                         @SerializedName("list") var list : List<ForecastDetail>)