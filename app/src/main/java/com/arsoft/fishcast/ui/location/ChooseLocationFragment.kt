package com.arsoft.fishcast.ui.location


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arsoft.fishcast.FishcastApplication
import com.arsoft.fishcast.R
import com.arsoft.fishcast.mvp.location.LocationPresenter
import com.arsoft.fishcast.mvp.location.LocationView
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_choose_location.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class ChooseLocationFragment : MvpAppCompatFragment(), LocationView, OnMapReadyCallback{

    companion object{
        fun getNewInstance() = ChooseLocationFragment().apply {
            arguments = Bundle().apply{
                //args
            }
        }
    }

    @Inject
    lateinit var router: Router

    @InjectPresenter
    lateinit var presenter: LocationPresenter

    @ProvidePresenter
    internal fun providePresenter(): LocationPresenter {
        return LocationPresenter(router)
    }

    init {
        FishcastApplication.INSTANCE.getAppComponent()!!.inject(this)
    }

    private lateinit var mapView: MapView
    private lateinit var map: GoogleMap
    private  var latLng: LatLng? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_choose_location, container, false)

        mapView = view.findViewById(R.id.mapView)
        mapView.getMapAsync(this)
        mapView.onCreate(savedInstanceState)
        presenter = LocationPresenter(router)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chooseLocationBtn.setOnClickListener{
            if (latLng != null) {
                presenter.onChooseButtonPressed(latLng!!.latitude, latLng!!.longitude)
            } else {
                Toast.makeText(activity, "Put marker on map", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap!!
        map.isMyLocationEnabled
        map.setOnMapClickListener {
            latLng = it
            map.clear()
            map.addMarker(MarkerOptions().position(it))
        }
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
        super.onLowMemory()
    }

    //View implementation


}
