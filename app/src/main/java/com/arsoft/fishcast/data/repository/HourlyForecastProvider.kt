package com.arsoft.fishcast.data.repository

import com.arsoft.fishcast.data.request.OpenWeatherApiService

class HourlyForecastProvider {
    companion object {
        fun provideHourlyForecastRepository(): HourlyForecastRepository {
            return HourlyForecastRepository(OpenWeatherApiService.create())
        }
    }

}