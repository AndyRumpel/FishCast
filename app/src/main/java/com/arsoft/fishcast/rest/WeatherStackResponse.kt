package com.arsoft.fishcast.rest

data class Current(
    val observation_time : String,
    val temperature : Int,
    val weather_code : Int,
    val weather_icons : List<String>,
    val weather_descriptions : List<String>,
    val wind_speed : Int,
    val wind_degree : Int,
    val wind_dir : String,
    val pressure : Int,
    val precip : Int,
    val humidity : Int,
    val cloudcover : Int,
    val feelslike : Int,
    val uv_index : Int,
    val visibility : Int
)

data class WeatherStack(
    val request : Request,
    val location : Location,
    val current : Current
)

data class Location(
    val name : String,
    val country : String,
    val region : String,
    val lat : Double,
    val lon : Double,
    val timezone_id : String,
    val localtime : String,
    val localtime_epoch : Int,
    val utc_offset : Double
)

data class Request(
    val type : String,
    val query : String,
    val language : String,
    val unit : String
)