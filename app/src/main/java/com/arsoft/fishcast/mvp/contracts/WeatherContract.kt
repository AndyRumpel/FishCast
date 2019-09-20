package com.arsoft.fishcast.mvp.contracts

class WeatherContract {
    interface View: BaseContract.View {
        fun showWeather()
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage(error: String?)
    }

    abstract class Presenter: BaseContract.Presenter<View>() {
        abstract fun loadWeather()
    }
}