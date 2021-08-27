package com.first.weatherforecast.network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.first.weatherforecast.R
import com.first.weatherforecast.model.City

class CitiesAdapter(
    private val cities: List<City>,
    private val onCityClick: (City) -> Unit
) : RecyclerView.Adapter<CitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val cityView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_of_cities, parent, false)

        return CitiesViewHolder(cityView, onCityClick)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int = cities.size
}