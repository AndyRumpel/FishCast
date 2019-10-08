package com.arsoft.fishcast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.arsoft.fishcast.data.repository.HourlyForecastProvider
import com.arsoft.fishcast.data.repository.HourlyForecastRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val hourlyForecastRepository = HourlyForecastProvider.provideHourlyForecastRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      hourlyForecastRepository.getHourlyForecast("Kazan,ru", "5df1ab98f26e78000c834b16a80f8383")
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe({
              result ->
              textView.text = result.toString()
          }, {
              error ->
              error.printStackTrace()
          })

    }
}
