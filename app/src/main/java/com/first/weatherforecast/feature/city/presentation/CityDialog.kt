package com.first.weatherforecast.feature.city.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.first.weatherforecast.R
import com.google.android.material.textfield.TextInputLayout
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
        val latitudeLayout = dialog.findViewById<TextInputLayout>(R.id.latitudeInput)
        val latitude = dialog.findViewById<EditText>(R.id.latitude)
        val longitudeLayout = dialog.findViewById<TextInputLayout>(R.id.longitudeInput)
        val longitude = dialog.findViewById<EditText>(R.id.longitude)

        latitude.addTextChangedListener {
            latitudeLayout.error = getString(R.string.incorrect_latitude)
            latitudeLayout.isErrorEnabled = latitudeValidation(it.toString())
        }

        longitude.addTextChangedListener {
            longitudeLayout.error = getString(R.string.incorrect_longitude)
            longitudeLayout.isErrorEnabled = longitudeValidation(it.toString())
        }

        addButton.setOnClickListener {
            if (!latitudeLayout.isErrorEnabled && !longitudeLayout.isErrorEnabled) {
                setResult(latitude.text.toString(), longitude.text.toString())
            }
        }
        clearButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun latitudeValidation(it: String): Boolean {
        return it.isNotBlank() && (it.toDouble() > 90)
    }

    private fun longitudeValidation(it: String): Boolean {
        return it.isNotBlank() && (it.toDouble() > 180)
    }

    private fun setResult(latitude: String, longitude: String) {
        val dialog = dialog ?: return
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
        dialog.dismiss()
    }
}