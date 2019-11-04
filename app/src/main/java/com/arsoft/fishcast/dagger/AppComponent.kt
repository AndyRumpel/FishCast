package com.arsoft.fishcast.dagger

import com.arsoft.fishcast.dagger.module.NavigationModule
import com.arsoft.fishcast.ui.main.MainActivity
import com.arsoft.fishcast.ui.forecast.ForecastFragment
import com.arsoft.fishcast.ui.location.ChooseLocationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NavigationModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: ChooseLocationFragment)
    fun inject(fragment: ForecastFragment)
}