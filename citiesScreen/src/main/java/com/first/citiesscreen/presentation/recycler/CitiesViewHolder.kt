package com.first.citiesscreen.presentation.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.first.citiesscreen.R
import com.first.common.model.City

internal class CitiesViewHolder(
    cityView: View,
    private val onCityClick: (City) -> Unit,
    private val changeFavorite: (City) -> Unit,
    private val removeButtonClick: (City) -> Unit
) : RecyclerView.ViewHolder(cityView) {

    private val cityText: TextView = cityView.findViewById(R.id.city)
    private val removeCity: ImageView = cityView.findViewById(R.id.deleteButton)
    private val favorite: ImageView = cityView.findViewById(R.id.favorite)
    private var city: City? = null

    init {
        cityView.setOnClickListener {
            city?.let(onCityClick::invoke)
        }

        cityView.setOnLongClickListener {
            city?.let(changeFavorite::invoke)
            true
        }

        removeCity.setOnClickListener {
            city?.let(removeButtonClick::invoke)
        }

        favorite.setOnClickListener {
            city?.let(changeFavorite::invoke)
        }

    }

    fun bind(city: City) {
        this.city = city
        cityText.text = city.name
        favorite.isVisible = city.favorite
    }
}