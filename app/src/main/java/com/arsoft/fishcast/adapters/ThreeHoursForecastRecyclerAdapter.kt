package com.arsoft.fishcast.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arsoft.fishcast.R
import com.arsoft.fishcast.data.request.Result
import com.arsoft.fishcast.data.models.ThreeHoursForecastModel
import com.arsoft.fishcast.utils.MyDateTimeFormatter
import com.arsoft.fishcast.utils.TextConverter

class ThreeHoursForecastRecyclerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val forecastList: ArrayList<ThreeHoursForecastModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.weather_recycler_unit, parent, false)
        return ThreeHoursForecastViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return forecastList.count()
    }

    fun setupForecastList(requestResult: Result) {
        forecastList.clear()
        for(element in requestResult.list) {
            forecastList.add(
                with(element) {
                    ThreeHoursForecastModel(
                        temperature = TextConverter.deleteDigitsAfterDots(main.temp) + "°",
                        weatherDescription = weather[0].description,
                        time = MyDateTimeFormatter.timeFormat((1000*dt)),
                        date = MyDateTimeFormatter.dateFormat((1000*dt))
                    )
                }
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ThreeHoursForecastViewHolder).bind(forecastList[position])
    }

    class ThreeHoursForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val temperatureTxt = itemView.findViewById<TextView>(R.id.temperature_txt)
        private val weatherDescription = itemView.findViewById<TextView>(R.id.weather_description_txt)
        private val time = itemView.findViewById<TextView>(R.id.time_txt)
        private val date = itemView.findViewById<TextView>(R.id.date_txt)

        fun bind(model: ThreeHoursForecastModel) {

            temperatureTxt.text = model.temperature
            weatherDescription.text = model.weatherDescription
            time.text = model.time
            date.text = model.date

        }
    }
}