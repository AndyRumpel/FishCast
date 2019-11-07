package com.arsoft.fishcast.ui.main

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arsoft.fishcast.FishcastApplication
import com.arsoft.fishcast.R
import com.arsoft.fishcast.Screens
import com.google.android.gms.location.FusedLocationProviderClient
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject


class MainActivity : MvpAppCompatActivity(){

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val navigator = object : SupportAppNavigator(this, R.id.main_container) {
        override fun applyCommands(commands: Array<Command>) {
            super.applyCommands(commands)
            supportFragmentManager.executePendingTransactions()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        FishcastApplication.INSTANCE.getAppComponent()!!.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION), 0)
        }

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationProvider = LocationManager.NETWORK_PROVIDER
        val lastKnownLocation = locationManager.getLastKnownLocation(locationProvider)



        if (lastKnownLocation != null) {
            navigator.applyCommands(arrayOf(Replace(Screens.ForecastScreen(lastKnownLocation.latitude, lastKnownLocation.longitude))))
        } else {
            navigator.applyCommands(arrayOf(Replace(Screens.ChooseLocationScreen())))
        }
    }

    override fun onResumeFragments() {
        navigatorHolder.setNavigator(navigator)
        super.onResumeFragments()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
