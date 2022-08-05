package com.example.mapapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mapapplication.databinding.ActivityMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initMap()
    }

    private fun initMap() {
        (supportFragmentManager.findFragmentById(R.id.fragment_map) as? SupportMapFragment)?.getMapAsync {
            generateMarker(it)
        }
    }

    private fun generateMarker(googleMap: GoogleMap) {
        val markerOptions = MarkerOptions()
        val latLng = LatLng(-6.1753871, 106.8249641)
        markerOptions.position(latLng).icon(null).title("Monas").snippet("Jakarta")
        googleMap.apply {
            uiSettings.isMapToolbarEnabled = false
            setOnMarkerClickListener {
                it.showInfoWindow()
                true
            }
            pointToPosition(latLng, this)
            addMarker(markerOptions)?.showInfoWindow()
        }
    }

    private fun pointToPosition(position: LatLng, googleMap: GoogleMap) {
        val cameraPosition = CameraPosition.Builder()
            .target(position)
            .zoom(10f).build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}