package com.example.mywheaterapp.repository.model

import com.google.gson.annotations.SerializedName

data class City(@SerializedName("name") var cityName : String,
                @SerializedName("country") var country : String)