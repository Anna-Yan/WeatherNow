package com.example.mywheaterapp.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HourInfoList(

    @SerializedName("dt")
    @Expose
    val dt: Int? = null,
    @SerializedName("main")
    @Expose
    val main: Main? = null, //
    @SerializedName("weather")
    @Expose
    val weather: List<Weather>? = null,
    @SerializedName("dt_txt")
    @Expose
    val dtTxt: String? = null) //
{

    override fun toString(): String {
        return "HourInfoList(dt=$dt, main=$main, weather=$weather, dtTxt=$dtTxt)"
    }
}