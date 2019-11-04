package com.arsoft.fishcast

import androidx.fragment.app.Fragment
import com.arsoft.fishcast.ui.forecast.ForecastFragment
import com.arsoft.fishcast.ui.location.ChooseLocationFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class ForecastScreen(private val lat: Double, private val lon: Double): SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ForecastFragment.getNewInstance(lat, lon)
        }
    }

    class ChooseLocationScreen: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ChooseLocationFragment.getNewInstance()
        }
    }

}