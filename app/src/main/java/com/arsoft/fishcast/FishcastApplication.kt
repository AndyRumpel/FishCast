package com.arsoft.fishcast

import android.app.Application
import com.arsoft.fishcast.dagger.AppComponent
import com.arsoft.fishcast.dagger.DaggerAppComponent

class FishcastApplication : Application() {

    companion object {
        lateinit var INSTANCE: FishcastApplication
    }

    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    fun getAppComponent(): AppComponent? {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().build()
        }
        return appComponent
    }


}