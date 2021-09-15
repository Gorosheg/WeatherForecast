package com.first.weatherforecast.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.first.weatherforecast.R
import com.first.weatherforecast.model.City
import com.first.weatherforecast.ui.recycler.CitiesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CitiesActivity : AppCompatActivity(), CityAddListener {

    private var adapter: CitiesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        val recyclerView: RecyclerView = findViewById(R.id.cityList)
        val addCity: FloatingActionButton = findViewById(R.id.dialog_button)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CitiesAdapter(
            items = buildList(),
            onCityClick = ::navigateToWeatherScreen
        )

        recyclerView.adapter = adapter

        addCity.setOnClickListener { showCityDialog() }
    }

    private fun showCityDialog() {
        val cityDialog = CityDialog()
        cityDialog.show(supportFragmentManager, CityDialog::class.qualifiedName)
    }

    private fun buildList(): MutableList<City> {
        return mutableListOf(
            City(59.939999, 30.315877, "Санкт-Петербург"),
            City(55.7522, 37.6156, "Москва"),
            City(60.1695, 24.9354, "Хельсинки")
        )
    }

    private fun navigateToWeatherScreen(city: City) {
        val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra(CITY_KEY, city)
        startActivity(intent)
    }

    override fun onCityAdd(latitude: Double, longitude: Double) {
        val adapter = adapter ?: return
        val newCity = City(latitude, longitude, "")
        adapter.items += newCity
        //adapter.notifyDataSetChanged() // Обновляем всё адаптер
        adapter.notifyItemInserted(adapter.itemCount - 1) // Говорим, что добавили элемент в конец
    }

    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}