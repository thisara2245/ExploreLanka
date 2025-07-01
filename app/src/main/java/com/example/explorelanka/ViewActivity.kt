package com.example.explorelanka

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng

class ViewActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        findViewById<Button>(R.id.mapButton).setOnClickListener {
            // Focus map on specific coordinates
            val location = LatLng(25.1234, 91.5678)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 14f))
        }

        findViewById<ImageView>(R.id.backButton).setOnClickListener {
            onBackPressed()
        }
    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap

        // Add example marker
        val placeLatLng = LatLng(25.1234, 91.5678)
        googleMap.addMarker(
            MarkerOptions().position(placeLatLng).title("Niladri Reservoir")
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(placeLatLng, 12f))

        // Enable location if permission granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            googleMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }
}
