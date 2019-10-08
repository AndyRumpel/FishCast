package com.arsoft.fishcast.data.repository

import com.arsoft.fishcast.data.request.OpenWeatherApiService
import com.arsoft.fishcast.data.request.Result
import io.reactivex.Observable
import retrofit2.Call

class HourlyForecastRepository(val apiService: OpenWeatherApiService) {
    fun getHourlyForecast(query: String, appid: String): Observable<Result> {
        return apiService.getForecastHourly(query = query, appid = appid)
    }
}