package com.first.citiesscreen.presentation.recycler

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.first.citiesscreen.R
import com.first.common.model.City

internal class CitiesViewHolder(
    cityView: View,
    private val onCityClick: (City) -> Unit,
    private val removeButtonClick: (City) -> Unit
) : RecyclerView.ViewHolder(cityView) {

    private var cityText: TextView = cityView.findViewById(R.id.city)
    private var rootLayout: LinearLayout = cityView.findViewById(R.id.rootLayout)
    private var removeCity: Button = cityView.findViewById(R.id.deleteButton)
    private var city: City? = null

    init {
        rootLayout.setOnClickListener {
            city?.let(onCityClick::invoke)
        }
        removeCity.setOnClickListener {
            city?.let(removeButtonClick::invoke)
        }
    }

    fun bind(city: City) {
        this.city = city
        cityText.text = city.name
    }
}