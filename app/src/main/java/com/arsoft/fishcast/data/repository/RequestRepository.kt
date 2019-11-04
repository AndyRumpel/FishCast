package com.arsoft.fishcast.data.repository

import com.arsoft.fishcast.data.request.OpenWeatherApiService
import com.arsoft.fishcast.data.request.Result
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call

class RequestRepository(val apiService: OpenWeatherApiService) {
    fun getHourlyForecast(query: String, appid: String): Observable<Result> {
        return apiService.getForecastHourly(query, appid)
    }

    fun getThreeHoursForecast(lat: Double, lon: Double, units: String, lang: String, appid: String): Single<Result> {
        return apiService.getThreeHoursForecast(lat, lon, units, lang, appid)
    }
}