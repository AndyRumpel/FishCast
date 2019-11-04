package com.arsoft.fishcast.ui.forecast


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arsoft.fishcast.R
import com.arsoft.fishcast.adapters.ThreeHoursForecastRecyclerAdapter
import com.arsoft.fishcast.data.request.Result
import com.arsoft.fishcast.mvp.forecast.ThreeHoursForecastPresenter
import com.arsoft.fishcast.mvp.forecast.ThreeHoursForecastView
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastFragment : MvpAppCompatFragment(), ThreeHoursForecastView {
    private val TAG = "MyTag"


    @InjectPresenter
    lateinit var threeHoursForecastPresenter: ThreeHoursForecastPresenter

    private lateinit var adapter: ThreeHoursForecastRecyclerAdapter
    private lateinit var threeHoursForecastRecyclerView: RecyclerView

    companion object {

        fun getNewInstance(lat: Double, lon: Double) = ForecastFragment().apply {
            arguments = Bundle().apply {
                putDouble("lat", lat)
                putDouble("lon", lon)
            }
        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)
        threeHoursForecastRecyclerView = view.findViewById(R.id.three_hours_forecast_recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            threeHoursForecastPresenter.provideForecast(arguments!!.getDouble("lat"), arguments!!.getDouble("lon"))
        }

        adapter = ThreeHoursForecastRecyclerAdapter()
        threeHoursForecastRecyclerView.adapter = adapter
        threeHoursForecastRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }
    //  View implementation
    override fun loadResult(result: Result) {
        adapter.setupForecastList(result)
    }


    override fun showLoading() {
        loading_txt.visibility = View.VISIBLE
        threeHoursForecastRecyclerView.visibility = View.INVISIBLE
    }

    override fun hideLoadind() {
        loading_txt.visibility = View.INVISIBLE
        threeHoursForecastRecyclerView.visibility = View.INVISIBLE
    }

    override fun showNothing() {
        loading_txt.visibility = View.INVISIBLE
        threeHoursForecastRecyclerView.visibility = View.INVISIBLE
    }

    override fun showForecast() {
        loading_txt.visibility = View.GONE
        threeHoursForecastRecyclerView.visibility = View.VISIBLE
        adapter.notifyDataSetChanged()
    }

    override fun showError(errorText: String) {
        Toast.makeText(activity, errorText, Toast.LENGTH_LONG).show()
        Log.e(TAG, errorText)
    }
}
