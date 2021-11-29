package com.first.weatherforecast.citiesPresentation.cities

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.first.weatherforecast.R

class CityDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        return AlertDialog.Builder(activity)
            .setTitle(getString(R.string.CityDialogTitle))
            .setView(R.layout.fragment_city_dialog)
            .setPositiveButton(getString(R.string.add)) { _, _ ->
                exitWithResult()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .create()
    }

    private fun exitWithResult() {
        val dialog = dialog ?: return
        val latitude = dialog.findViewById<EditText>(R.id.latitude).text.toString()
        val longitude = dialog.findViewById<EditText>(R.id.longitude).text.toString()
        val cityName: String = dialog.findViewById<EditText>(R.id.name).text.toString()

        if (cityName != "") {
            (activity as CityAddListener).onCityAdd(
                name = cityName
            )
        } else if (latitude != "" && longitude != "") {
            (activity as CityAddListener).onCityAdd(
                latitude = latitude.toDouble(),
                longitude = longitude.toDouble()
            )
        }
    }

}