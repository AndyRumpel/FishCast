package com.arsoft.fishcast.ui.forecast


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arsoft.fishcast.FishcastApplication
import com.arsoft.fishcast.R
import com.arsoft.fishcast.adapters.ThreeHoursForecastRecyclerAdapter
import com.arsoft.fishcast.data.request.location_image.LocationImageResult
import com.arsoft.fishcast.data.request.forecast.Result
import com.arsoft.fishcast.mvp.forecast.ThreeHoursForecastPresenter
import com.arsoft.fishcast.mvp.forecast.ThreeHoursForecastView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_forecast.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ForecastFragment : MvpAppCompatFragment(), ThreeHoursForecastView {

    companion object {
        fun getNewInstance(lat: Double, lon: Double) = ForecastFragment().apply {
            arguments = Bundle().apply {
                putDouble("lat", lat)
                putDouble("lon", lon)
            }
        }
    }

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var threeHoursForecastPresenter: ThreeHoursForecastPresenter

    @ProvidePresenter
    internal fun providePresenter(): ThreeHoursForecastPresenter {
        return ThreeHoursForecastPresenter(router)
    }

    init {
        FishcastApplication.INSTANCE.getAppComponent()!!.inject(this)
    }

    private lateinit var adapter: ThreeHoursForecastRecyclerAdapter
    private lateinit var threeHoursForecastRecyclerView: RecyclerView
    private val TAG = "MyTag"
    private val LOCATION_IMAGE_REQUEST_URL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)
        threeHoursForecastRecyclerView = view.findViewById(R.id.three_hours_forecast_recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            threeHoursForecastPresenter.provideForecast(arguments!!.getDouble("lat"), arguments!!.getDouble("lon"))
            threeHoursForecastPresenter.provideLocationImage(arguments!!.getDouble("lat").toString() + ","
                                                                    + arguments!!.getDouble("lon").toString())
        }

        adapter = ThreeHoursForecastRecyclerAdapter()
        threeHoursForecastRecyclerView.adapter = adapter
        threeHoursForecastRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.location_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.choose_on_map -> threeHoursForecastPresenter.onChooseLocationOnMapClicked()
        }

        return super.onOptionsItemSelected(item)
    }

    //  View implementation
    override fun loadResult(result: Result) {
        adapter.setupForecastList(result)
    }

    override fun loadLocationImage(locationImageResult: LocationImageResult) {
        Glide.with(activity!!.applicationContext)
            .load(LOCATION_IMAGE_REQUEST_URL + locationImageResult.results[0].photos[0].photo_reference + "&key=AIzaSyCuITo9Z0DT3PMiPBbzBJIek5COi5HIN9Y")
            .centerCrop()
            .into(locaton_img)
        location_name_txt.text = locationImageResult.results[0].vicinity
    }

    override fun showLoading() {
        loading_cpv.visibility = View.VISIBLE
        threeHoursForecastRecyclerView.visibility = View.INVISIBLE
        location_name_txt.visibility = View.INVISIBLE
        locaton_img.visibility = View.INVISIBLE
    }

    override fun hideLoadind() {
        loading_cpv.visibility = View.GONE
        threeHoursForecastRecyclerView.visibility = View.INVISIBLE
    }

    override fun showNothing() {
        loading_cpv.visibility = View.GONE
        threeHoursForecastRecyclerView.visibility = View.INVISIBLE
    }

    override fun showForecast() {
        threeHoursForecastRecyclerView.visibility = View.VISIBLE
        location_name_txt.visibility = View.VISIBLE
        locaton_img.visibility = View.VISIBLE
        adapter.notifyDataSetChanged()
    }

    override fun showError(errorText: String) {
        Toast.makeText(activity, errorText, Toast.LENGTH_LONG).show()
        Log.e(TAG, errorText)
    }
}
