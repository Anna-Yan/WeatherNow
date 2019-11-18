package com.example.mywheaterapp.repository.model


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class WeatherFor5Days (

    @SerializedName("cod")
    @Expose
    private val cod: String? = null,
    @SerializedName("message")
    @Expose
    private val message: Double? = null,
    @SerializedName("cnt")
    @Expose
    private val cnt: Int? = null,
    @SerializedName("list")
    @Expose
    val list: List<HourInfoList>? = null,
    @SerializedName("city")
    @Expose
    private val city: City? = null
  )
{

    override fun toString(): String {
        return "WeatherFor5Days(cod=$cod, message=$message, cnt=$cnt, list=$list, city=$city)"
    }
}
