package com.example.mywheaterapp.repository.api

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



object RetrofitClient {
    private var retrofitJson: Retrofit? = null
    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"


    fun getClientJson(context: Context): Retrofit {
        if (retrofitJson == null) {
            retrofitJson = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createClient())
                .build()
        }
        return retrofitJson!!
    }

    private fun createClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(OpenWeatherInterceptor())
        builder.readTimeout(10, TimeUnit.MINUTES)
        builder.writeTimeout(10, TimeUnit.MINUTES)

        return builder.build()
    }
}