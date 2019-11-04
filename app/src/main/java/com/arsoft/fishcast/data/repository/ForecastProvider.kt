package com.arsoft.fishcast.data.repository

import com.arsoft.fishcast.data.request.OpenWeatherApiService

class ForecastProvider {
    companion object {
        fun provideHourlyForecastRepository(): RequestRepository {
            return RequestRepository(OpenWeatherApiService.create())
        }
    }

}