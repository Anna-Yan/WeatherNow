package com.example.mywheaterapp.repository

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mywheaterapp.util.DateUtil
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class WeatherLocal : Parcelable {
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0
    @SerializedName("city")
    var city: String = ""
    @SerializedName("temp")
    var temp: Double = 0.0
    @SerializedName("pressure")
    @Expose
    var pressure: Int = 0
    @SerializedName("humidity")
    @Expose
     var humidity: Int = 0
    @SerializedName("temp_min")
    @Expose
     var tempMin: Double = 0.0
    @SerializedName("temp_max")
    @Expose
    var tempMax: Double = 0.0
    var description: String = ""
    var increment = 0

    constructor() {
        id = (++increment).toLong()
    }

    protected constructor(parcel: Parcel) {
        id = DateUtil.randomNumber
        city = parcel.readString()!!
        pressure = parcel.readInt()
        humidity = parcel.readInt()
        temp = parcel.readDouble()
        description = parcel.readString()!!
        tempMin = parcel.readDouble()
        tempMax = parcel.readDouble()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(id)
        dest.writeString(city)
        dest.writeInt(pressure)
        dest.writeInt(humidity)
        dest.writeDouble(temp)
        dest.writeDouble(tempMin)
        dest.writeDouble(tempMax)
        dest.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }


    override fun equals(obj: Any?): Boolean {
        if (obj === this)
            return true

        val article = obj as WeatherLocal?
        return article!!.id == this.id
    }


    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<WeatherLocal>{
            override fun createFromParcel(parcel: Parcel): WeatherLocal {
                return WeatherLocal(parcel)
            }

            override fun newArray(size: Int): Array<WeatherLocal?> {
                return arrayOfNulls(size)
            }
        }

    }

}