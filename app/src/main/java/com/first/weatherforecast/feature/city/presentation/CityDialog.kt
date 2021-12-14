package com.first.weatherforecast.feature.city.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.first.weatherforecast.R
import java.util.*

class CityDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Objects.requireNonNull(dialog)?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(R.layout.fragment_city_dialog, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dialog = dialog ?: return
        val addButton = dialog.findViewById<Button>(R.id.addButton)
        val clearButton = dialog.findViewById<Button>(R.id.clearButton)
        addButton.setOnClickListener {
            setResult()
        }
        clearButton.setOnClickListener {
            dialog.dismiss()
        }

    }

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        return AlertDialog.Builder(activity)
            .setTitle(getString(R.string.CityDialogTitle))
            .setView(R.layout.fragment_city_dialog)
            .setPositiveButton(getString(R.string.add)) { _, _ ->
                setResult()
            }
            .setOnCancelListener {

            }
            .setNegativeButton(getString(R.string.cancel), null)
            .create()
    }*/

    private fun setResult() {
        val dialog = dialog ?: return
        val latitude = dialog.findViewById<EditText>(R.id.latitude).text.toString()
        val longitude = dialog.findViewById<EditText>(R.id.longitude).text.toString()
        val cityName: String = dialog.findViewById<EditText>(R.id.name).text.toString()
        val errorMessage = dialog.findViewById<TextView>(R.id.errorMessage)

        if (cityName != "") {
            (activity as CityAddListener).onCityAdd(
                name = cityName
            )
        } else if (latitude != "" && longitude != "") {
            if (validation(latitude, longitude, errorMessage)) {
                (activity as CityAddListener).onCityAdd(
                    latitude = latitude.toDouble(),
                    longitude = longitude.toDouble()
                )
                dialog.dismiss()
            }
        }
    }

    private fun validation(
        latitude: String,
        longitude: String,
        errorMessage: TextView
    ): Boolean {
        if (latitude.toDouble() < 0 ||
            latitude.toDouble() > 90 ||
            longitude.toDouble() < 0 ||
            longitude.toDouble() > 180
        ) {
            errorMessage.text = getString(R.string.wrongCoordinates)
            errorMessage.isVisible = true
            return false
        }
        return true
    }

}