package com.arsoft.fishcast.data.request.forecast

data class Result(
    val cod : Int,
    val message : Double,
    val cnt : Int,
    val list : kotlin.collections.List<List<Any?>>,
    val city : City
)

data class City (
    val id : Int,
    val name : String,
    val coord : Coord,
    val country : String,
    val population : Int
)

data class Clouds (
    val all : Int
)

data class Coord (
    val lat : Double,
    val lon : Double
)
data class List<T>(
    val dt : Long,
    val main : Main,
    val weather : kotlin.collections.List<Weather>,
    val clouds : Clouds,
    val wind : Wind,
    val sys : Sys,
    val dt_txt : String,
    var baitProbabilityPercentage : Int = 0
)

data class Main (
    val temp : Double,
    val temp_min : Double,
    val temp_max : Double,
    val pressure : Double,
    val sea_level : Double,
    val grnd_level : Double,
    val humidity : Int,
    val temp_kf : Double
)

data class Sys (
    val pod : String
)

data class Weather (
    val id : Int,
    val main : String,
    val description : String,
    val icon : String
)

data class Wind (
    val speed : Double,
    val deg : Double
)
