package com.arsoft.fishcast.rest

data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Integer,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)

data class Clouds(
    val all: Int
)

data class Coord(
    val lat: Double,
    val lon: Double
)

data class Example(
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: kotlin.collections.List<List>,
    val city:City
)

data class List(

    val dt: Int,
    val main: Main,
    val weather: kotlin.collections.List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val sys: Sys,
    val dtTxt: String,
    val rain: Rain
)

data class Main(
    val temp: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Double,
    val seaLevel: Double,
    val grndLevel: Double,
    val humidity: Int,
    val tempKf: Int
)

data class Rain(
    val _3h: Double
)

data class Sys(
    val pod: String
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Wind(
    val speed: Double,
    val deg: Double
)