package com.first.feature.citiesscreen.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.first.citiesscreen.R
import com.first.citiesscreen.R.layout
import com.first.citiesscreen.R.string
import com.first.common.model.City
import com.first.common.model.Coordinates
import com.first.common.util.DIALOG_KEY
import com.first.common.util.EXTRA_DIALOG_KEY
import com.google.android.material.textfield.TextInputLayout
import java.util.*

internal class CityDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Objects.requireNonNull(dialog)?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return inflater.inflate(layout.fragment_city_dialog, null, false)
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
            latitudeLayout.error = getString(string.incorrect_latitude)
            latitudeLayout.isErrorEnabled = latitudeValidation(it.toString())
        }

        longitude.addTextChangedListener {
            longitudeLayout.error = getString(string.incorrect_longitude)
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
        return it.contains(',')
                || it.contains('-')
                || it.contains(' ')
                || it.isNotBlank() && (it.toDouble() > 90)
    }

    private fun longitudeValidation(it: String): Boolean {
        return it.contains(',')
                || it.contains('-')
                || it.contains(' ') || it.isNotBlank()
                && (it.toDouble() > 180)
    }

    private fun setResult(latitude: String, longitude: String) {
        val dialog = dialog ?: return
        val cityName: String = dialog.findViewById<EditText>(R.id.name).text.toString()

        if (cityName != "") {
            setFragmentResult(
                DIALOG_KEY, bundleOf(
                    Pair<String, Any?>(EXTRA_DIALOG_KEY, City(name = cityName))
                )
            )

        } else if (latitude != "" && longitude != "") {
            setFragmentResult(
                DIALOG_KEY, bundleOf(
                    Pair<String, Any?>(
                        EXTRA_DIALOG_KEY, City(
                            Coordinates(
                                latitude = latitude.toDouble(), longitude = longitude.toDouble()
                            )
                        )
                    )
                )
            )
        }

        dialog.dismiss()

    }
}