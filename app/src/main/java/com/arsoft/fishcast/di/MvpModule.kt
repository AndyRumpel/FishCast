package com.arsoft.fishcast.di

import com.arsoft.fishcast.mvp.presenters.WeatherPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MvpModule {

    @Provides
    @Singleton
    fun provideWeatherPresenter(): WeatherPresenter = WeatherPresenter()

}