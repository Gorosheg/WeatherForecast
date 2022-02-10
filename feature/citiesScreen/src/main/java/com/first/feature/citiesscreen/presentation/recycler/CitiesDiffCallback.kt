package com.first.feature.citiesscreen.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.first.common.model.City

internal class CitiesDiffCallback(
    private val oldList: List<City>,
    private val newList: List<City>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCity = oldList[oldItemPosition]
        val newCity = newList[newItemPosition]
        return oldCity.coordinates == newCity.coordinates
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCity = oldList[oldItemPosition]
        val newCity = newList[newItemPosition]
        return oldCity == newCity
    }
}