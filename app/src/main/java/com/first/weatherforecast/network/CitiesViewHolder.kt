package com.first.weatherforecast.network

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.first.weatherforecast.R
import com.first.weatherforecast.model.City

class CitiesViewHolder(
    cityView: View,
    private val onCityClick: (City) -> Unit
) : RecyclerView.ViewHolder(cityView) {

    private var cityText: TextView = cityView.findViewById(R.id.City)
    private var cityRecycler: LinearLayout = cityView.findViewById(R.id.cityList)

    private var city: City? = null

    init {
        cityRecycler.setOnClickListener {
            city?.let(onCityClick::invoke)
        }
    }

    fun bind(city: City) {
        cityText.text = city.name
    }
}