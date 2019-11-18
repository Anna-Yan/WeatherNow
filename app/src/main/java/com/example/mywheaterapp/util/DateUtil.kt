package com.example.mywheaterapp.util

import java.text.SimpleDateFormat

object DateUtil {


    val randomNumber: Long
        get() = (Math.random() * (100000 - 0 + 1) + 0).toLong()


    fun getDate(dateString: String): String {

        try {
            val format1 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
            val date = format1.parse(dateString)
            val sdf = SimpleDateFormat("MM/dd")
            return sdf.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "xx"
        }

    }

    fun getTime(dateString: String): String {

        try {
            val format1 = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
            val date = format1.parse(dateString)
            val sdf = SimpleDateFormat("h:mm a")
            return sdf.format(date)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return "xx"
        }

    }
}