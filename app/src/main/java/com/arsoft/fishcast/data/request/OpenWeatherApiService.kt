package com.arsoft.fishcast.data.request

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApiService {

    @GET("forecast/hourly")
    fun getForecastHourly(
        @Query("q") query: String,
        @Query("appid") appid: String
    ): Observable<Result>

    companion object Factory {
        fun create(): OpenWeatherApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://samples.openweathermap.org/data/2.5/")
                .build()

            return retrofit.create(OpenWeatherApiService::class.java)
        }
    }

}