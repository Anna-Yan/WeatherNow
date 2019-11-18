package com.example.mywheaterapp.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mywheaterapp.repository.model.*
import com.example.mywheaterapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*


fun Activity.toast(toastMessage: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, toastMessage, duration).show()
}

fun Activity.toastError(toastMessage: ErrorTypes, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, toastMessage.name, duration).show()
}
fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Activity.showLoading() {
    loadingSpinner.visibility = View.VISIBLE
}

fun Activity.hideLoading() {
    loadingSpinner.visibility = View.GONE
}

fun AllResponse.toCurrentWeather() = CurrentWeather(
    name = name,
    temp = main.temp,
    tempMin =  main.tempMin,
    tempMax =  main.tempMax,
    humidity =  main.humidity,
    pressure = main.pressure,
    description = weather[0].description)


//Get only 5 first hours
fun WeatherFor5Days.toWeatherItemModel1() : List<WeatherItemModel>? {

    val itemList: MutableList<WeatherItemModel> = mutableListOf()

    for(it1 in list?.subList(0,5).orEmpty()) {
        var model= WeatherItemModel()
        model.temperature = it1.main!!.temp
        model.icon = it1.weather!![it1.weather.size - 1].icon
        model.date = ""
        model.degreeDay = it1.dtTxt.toString()
        itemList.add(model)
    }
    return itemList
}

//Get days
fun WeatherFor5Days.toWeatherItemModel2() : List<WeatherItemModel>? {

    val itemList: MutableList<WeatherItemModel> = mutableListOf()
    val listt: MutableList<HourInfoList> = mutableListOf()

    //Get 5 first days
    for (x in 1 until 40 step 8) {
        listt.add(list!![x])
    }
        for(it in listt ) {
            var model= WeatherItemModel()
            model.temperature = it.main!!.temp
            model.icon = it.weather!![it.weather.size - 1].icon
            model.date = it.dt.toString()
            model.degreeDay = it.dtTxt.toString()
            itemList.add(model)
        }

    return itemList
}


class WeatherViewModelFactory(val context: Application, val cityName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherViewModel(context,cityName) as T
    }

}