package com.arsoft.fishcast.mvp.location

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arsoft.fishcast.Screens
import ru.terrakok.cicerone.Router

@InjectViewState
class LocationPresenter(private var router: Router): MvpPresenter<LocationView>() {

    fun onChooseButtonPressed(lat: Double, lon: Double) {
        router.newRootChain(Screens.ForecastScreen(lat, lon))
    }
}