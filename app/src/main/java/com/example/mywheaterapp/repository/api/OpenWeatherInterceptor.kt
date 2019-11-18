package com.example.mywheaterapp.repository.api


import okhttp3.Interceptor
import okhttp3.Response


class OpenWeatherInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("APPID", ApiService.OPEN_WEATHER_API_KEY).
            addQueryParameter("mode", "json").
            addQueryParameter("units", "metric").build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}