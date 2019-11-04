package com.arsoft.fishcast.mvp.location

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.google.android.gms.maps.model.LatLng

@StateStrategyType(AddToEndSingleStrategy::class)
interface LocationView: MvpView {
}