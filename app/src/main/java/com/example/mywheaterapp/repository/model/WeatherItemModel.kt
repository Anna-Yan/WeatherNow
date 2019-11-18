package com.example.mywheaterapp.repository.model

data class WeatherItemModel(var degreeDay : String = "",
                            var icon : String = "01d",
                            var temperature:  Double = 0.0,
                            var date : String = "" )