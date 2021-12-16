package com.first.citiesscreen.presentation.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.first.citiesscreen.R
import com.first.common.model.City

class CitiesAdapter(
    val items: MutableList<City>,
    private val onCityClick: (City) -> Unit,
    private val removeCity: (City) -> Unit
) : RecyclerView.Adapter<CitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
        val cityView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_of_cities, parent, false)
        return CitiesViewHolder(cityView, onCityClick, removeCity)
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}