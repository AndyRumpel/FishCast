package com.arsoft.fishcast.mvp.forecast

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arsoft.fishcast.data.repository.ForecastProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

@InjectViewState
class ThreeHoursForecastPresenter: MvpPresenter<ThreeHoursForecastView>(){

    private val forecastRepository = ForecastProvider.provideHourlyForecastRepository()

    fun provideForecast(lat: Double, lon: Double) {
        viewState.showLoading()
        forecastRepository.getThreeHoursForecast(lat, lon, "metric", "ru", "5df1ab98f26e78000c834b16a80f8383")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                result ->
                viewState.hideLoadind()
                viewState.loadResult(result)
                viewState.showForecast()
            }, {
                error ->
                viewState.hideLoadind()
                viewState.showError(error.message.toString())
                error.printStackTrace()
            })

    }
}