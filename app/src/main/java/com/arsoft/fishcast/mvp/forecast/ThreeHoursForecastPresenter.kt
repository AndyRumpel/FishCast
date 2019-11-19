package com.arsoft.fishcast.mvp.forecast

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arsoft.fishcast.Screens
import com.arsoft.fishcast.data.repository.forecast.CalculateBaitProbability
import com.arsoft.fishcast.data.repository.forecast.DataProvider
import com.arsoft.fishcast.data.request.forecast.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router

@InjectViewState
class ThreeHoursForecastPresenter(private var router: Router): MvpPresenter<ThreeHoursForecastView>(){

    private val forecastRepository = DataProvider.provideHourlyForecastRepository()

    @SuppressLint("CheckResult")
    fun provideForecast(lat: Double, lon: Double) {
        viewState.showLoading()
        forecastRepository.getThreeHoursForecast(lat, lon, "metric", "ru", "5df1ab98f26e78000c834b16a80f8383")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

            .subscribe({
                result ->
                viewState.hideLoadind()
                viewState.loadWeather(CalculateBaitProbability.getFromAll(result))
                viewState.showForecast()
            }, {
                error ->
                viewState.hideLoadind()
                viewState.showError(error.message.toString())
                error.printStackTrace()
            })
    }


    fun onChooseLocationOnMapClicked() {
        router.navigateTo(Screens.ChooseLocationScreen())
    }
}