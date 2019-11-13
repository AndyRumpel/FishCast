package com.arsoft.fishcast.data.repository.forecast

import com.arsoft.fishcast.data.request.forecast.OpenWeatherApiService
import com.arsoft.fishcast.data.request.forecast.Result
import io.reactivex.Observable
import io.reactivex.Single

class ForecastRequestRepository(val apiService: OpenWeatherApiService) {
    fun getHourlyForecast(query: String, appid: String): Observable<Result> {
        return apiService.getForecastHourly(query, appid)
    }

    fun getThreeHoursForecast(lat: Double, lon: Double, units: String, lang: String, appid: String): Single<Result> {
        return apiService.getThreeHoursForecast(lat, lon, units, lang, appid)
    }
}