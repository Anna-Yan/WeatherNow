package com.example.mywheaterapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mywheaterapp.R
import com.example.mywheaterapp.repository.model.WeatherItemModel
import com.example.mywheaterapp.util.DateUtil
import kotlinx.android.synthetic.main.weather_list_item.view.*

class WeatherAdapter() : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    var weatherList = mutableListOf<WeatherItemModel>()

    fun addWeather(list : List<WeatherItemModel>){
        weatherList.clear()
        weatherList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        weatherList[position].let {
            holder.bind(weatherElement = it)
        }
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(weatherElement : WeatherItemModel) {
            itemView.tempText.text = "${weatherElement.temperature} °C "

            //Hours
            if(weatherElement.date.isEmpty()){
                if(weatherElement.degreeDay.substring(11,12).equals("0")){
                    itemView.dateText.text = weatherElement.degreeDay.substring(12,13)+"h"
                }else
                    itemView.dateText.text = weatherElement.degreeDay.substring(11,13)+"h"
            }else{
                //Days
                itemView.dateText.text = weatherElement.degreeDay.substring(5,10)
            }


            Glide.with(itemView.context)
                .load("http://openweathermap.org/img/w/${weatherElement.icon}.png")
                .into(itemView.weatherIcon)
        }

    }

}