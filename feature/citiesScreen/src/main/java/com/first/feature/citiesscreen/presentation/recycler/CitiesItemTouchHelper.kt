package com.first.feature.citiesscreen.presentation.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

internal class CitiesItemTouchHelper(
    private val swipe: (Int) -> Unit
) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
        //Remove swiped item from list and notify the RecyclerView
        val position = viewHolder.adapterPosition
        swipe.invoke(position)
    }

}