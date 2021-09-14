package com.first.weatherforecast.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.first.weatherforecast.R

class CityDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val builder = AlertDialog.Builder(activity)
        return builder
            .setTitle("Введите координаты города")
            .setView(R.layout.fragment_cities_dialog)
            .setPositiveButton("Добавить", null)
            .setNegativeButton("Отмена", null)
            .create()
    }
}