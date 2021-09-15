package com.first.weatherforecast.ui

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
            .setTitle(getString(R.string.coordinats))
            .setView(R.layout.fragment_city_dialog)
            .setPositiveButton(getString(R.string.add)) { _, _ ->
                exitWithResult()
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .create()
    }

    private fun exitWithResult() {
        val dialog = dialog ?: return
        val latitude = dialog.findViewById<EditText>(R.id.latitude).text
        val longitude = dialog.findViewById<EditText>(R.id.longitude).text

        (activity as CityAddListener).onCityAdd(
            latitude = latitude.toString().toDouble(),
            longitude = longitude.toString().toDouble()
        )
    }

}