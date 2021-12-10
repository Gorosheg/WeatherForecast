package com.first.weatherforecast.feature.city.presentation

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.first.weatherforecast.feature.city.presentation.CitiesActivity.Companion.LOCATION_PERMISSION_REQUEST_CODE

class UserLocation(
    private val locationManager: LocationManager,
    private val onStart: (Boolean) -> Unit,
    private val onResult: (Location) -> Unit
) {

    private val mLocationListener: LocationListener = LocationListener { handleLocation(it) }

    fun enableMyLocation(activity: Activity) {
        onStart.invoke(true)
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // показываем город по координатам
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                10, 1000f, mLocationListener
            )

        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun handleLocation(location: Location) {
        onResult.invoke(location)
        locationManager.removeUpdates(mLocationListener)
    }
}