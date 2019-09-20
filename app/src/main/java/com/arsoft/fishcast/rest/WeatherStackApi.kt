package com.arsoft.fishcast.rest

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherStackApi {

    // weather request
    @GET("current")
    fun getCurrentWeather(
        @Query("access_key") access_key: String = "900fb7d01a97805d72322984395b7db2",
        @Query("query") query: String = "Kazan"
    ): Maybe<WeatherStack>

}