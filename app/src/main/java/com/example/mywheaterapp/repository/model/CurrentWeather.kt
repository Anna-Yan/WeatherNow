package com.example.mywheaterapp.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrentWeather (
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("temp")
    var temp: Double? = null,
    @SerializedName("pressure")
    @Expose
    var pressure: Int? = null,
    @SerializedName("humidity")
    @Expose
    var humidity: Int? = null,
    @SerializedName("temp_min")
    @Expose
    var tempMin: Double? = null,
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double? = null,
    var description: String? = null)
{

    override fun toString(): String {
        return "Main{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", description='" + description + '\''.toString() +
                '}'.toString()
    }
}