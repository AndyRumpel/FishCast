package com.arsoft.fishcast.data.models

data class ThreeHoursForecastModel(
    val temperature: String,
    val weatherDescription: String,
    val time: String,
    val date: String
)