package com.arsoft.fishcast.di

import com.arsoft.fishcast.MainActivity
import com.arsoft.fishcast.mvp.presenters.WeatherPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, RestModule::class, MvpModule::class, WeatherModule::class))
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(weatherPresenter: WeatherPresenter)

}