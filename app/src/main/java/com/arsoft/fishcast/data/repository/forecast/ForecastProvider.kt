package com.arsoft.fishcast.data.repository.forecast

import com.arsoft.fishcast.data.request.forecast.OpenWeatherApiService

class DataProvider {
    companion object {
        fun provideHourlyForecastRepository(): ForecastRequestRepository {
            return ForecastRequestRepository(
                OpenWeatherApiService.create()
            )
        }
    }

}