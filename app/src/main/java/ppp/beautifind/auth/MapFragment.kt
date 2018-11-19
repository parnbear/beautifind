package ppp.beautifind.auth


import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_browse.*
import ppp.beautifind.R

class MapFragment : Fragment(), OnMapReadyCallback{

    private lateinit var map: GoogleMap
    private var mapja : MapView? = null

    private var searchView : EditText? = null
    private var submitSearch : Button? = null

    private var markerHere : MarkerOptions? = null

    private var marker1 : MarkerOptions? = null
    private var marker2 : MarkerOptions? = null
    private var marker3 : MarkerOptions? = null
    private var marker4 : MarkerOptions? = null
    private var marker5 : MarkerOptions? = null

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        val here = LatLng(16.752725, 100.196741)
        markerHere = MarkerOptions().position(here).title("You're here!")

        map.addMarker(markerHere).showInfoWindow()
        //map.moveCamera(CameraUpdateFactory.newLatLng(here))

        var cameraPosition = CameraPosition.Builder().zoom(16.5F).target(here).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        val shop1 = LatLng(16.752303, 100.195689)
        val shop2 = LatLng(16.754666, 100.195442)
        val shop3 = LatLng(16.753863, 100.197015)
        val shop4 = LatLng(16.751880, 100.197113)
        val shop5 = LatLng(16.751050, 100.196613)

        marker1 = MarkerOptions().position(shop1).title("Shop1")
        marker2 = MarkerOptions().position(shop2).title("Shop2")
        marker3 = MarkerOptions().position(shop3).title("Shop3")
        marker4 = MarkerOptions().position(shop4).title("Shop4")
        marker5 = MarkerOptions().position(shop5).title("Shop5")

    }

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       var v = inflater.inflate(R.layout.fragment_map, container, false)

       mapja = v.findViewById(R.id.mapja) as MapView?
       searchView = v.findViewById(R.id.searchView) as EditText?
       submitSearch = v.findViewById(R.id.submitSearch) as Button?

       submitSearch!!.setOnClickListener( {
           var searchText = searchView!!.text.toString()

           if ( searchText == "zhe" || searchText == "zhecosmatic") {
               map.addMarker(markerHere).showInfoWindow()
               map.addMarker(marker1)
               map.addMarker(marker2)
               map.addMarker(marker3)
               map.addMarker(marker4)
               map.addMarker(marker5)
           } else {
               map.clear()
               map.addMarker(markerHere).showInfoWindow()
           }
       })

       mapja!!.onCreate(savedInstanceState)
       mapja!!.onResume()

       //map = mapja!!.getMapAsync(this)

       mapja!!.getMapAsync(this)

       return v
    }

    //override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
     //   inflater.inflate(R.layout.fragment_map, container, false)


    companion object {
        fun newInstance(): MapFragment = MapFragment()
    }


}
