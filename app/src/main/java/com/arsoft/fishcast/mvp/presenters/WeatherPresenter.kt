package com.arsoft.fishcast.mvp.presenters

import com.arsoft.fishcast.di.App
import com.arsoft.fishcast.mvp.contracts.WeatherContract
import com.arsoft.fishcast.rest.WeatherStackApi
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherPresenter: WeatherContract.Presenter() {

    @Inject
    lateinit var weatherStackApi: WeatherStackApi

    init {
        App.appComponent.inject(this)
    }

    override fun loadWeather() {
        view.showProgress()
        subscribe(weatherStackApi.getCurrentWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete {
                view.hideProgress()
            }
            .subscribe({
                view.hideProgress()
                view.showWeather()
            }, {
                view
            })



        )

    }

}