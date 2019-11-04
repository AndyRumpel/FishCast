package com.arsoft.fishcast.mvp.forecast

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.arsoft.fishcast.data.request.Result

@StateStrategyType(AddToEndSingleStrategy::class)
interface ThreeHoursForecastView: MvpView {
    fun showLoading()
    fun hideLoadind()
    fun showForecast()
    fun loadResult(result: Result)
    fun showNothing()
    fun showError(errorText: String)
}