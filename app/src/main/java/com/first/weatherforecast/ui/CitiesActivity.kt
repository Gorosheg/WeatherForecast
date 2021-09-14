package com.first.weatherforecast.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.first.weatherforecast.R
import com.first.weatherforecast.model.City
import com.first.weatherforecast.network.CitiesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CitiesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        val recyclerView: RecyclerView = findViewById(R.id.cityList)
        val addCity: FloatingActionButton = findViewById(R.id.dialog_button)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = CitiesAdapter(
            cities = buildList(),
            onCityClick = ::navigateToWeatherScreen
        )

        addCity.setOnClickListener { showCityDialog() }
    }

    private fun showCityDialog() {
        CityDialog().show(supportFragmentManager, CityDialog::class.qualifiedName)
    }

    private fun buildList(): List<City> {
        return listOf(
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

    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}