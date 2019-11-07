package com.arsoft.fishcast.mvp.forecast

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arsoft.fishcast.Screens
import com.arsoft.fishcast.data.repository.forecast.DataProvider
import com.arsoft.fishcast.data.repository.location_image.LocationImageProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router

@InjectViewState
class ThreeHoursForecastPresenter(private var router: Router): MvpPresenter<ThreeHoursForecastView>(){

    private val forecastRepository = DataProvider.provideHourlyForecastRepository()
    private val locationImageRequestRepository = LocationImageProvider.provideLocationImageRepository()

    @SuppressLint("CheckResult")
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

    @SuppressLint("CheckResult")
    fun provideLocationImage(location: String) {
        locationImageRequestRepository.getLocationImage(location, 50000, "AIzaSyCuITo9Z0DT3PMiPBbzBJIek5COi5HIN9Y" )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ locationImageResult->
                viewState.loadLocationImage(locationImageResult)
            }, { error ->
                error.printStackTrace()
                viewState.showError(error.localizedMessage)
            })
    }

    fun onChooseLocationOnMapClicked() {
        router.navigateTo(Screens.ChooseLocationScreen())
    }
}