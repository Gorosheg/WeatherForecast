package com.first.weatherforecast.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.first.weatherforecast.R
import com.first.weatherforecast.database.CitiesDatabaseDatasource
import com.first.weatherforecast.database.model.CityEntity
import com.first.weatherforecast.network.loadWeather
import com.first.weatherforecast.ui.model.City
import com.first.weatherforecast.ui.recycler.CitiesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CitiesActivity : AppCompatActivity(), CityAddListener {

    private var adapter: CitiesAdapter? = null
    private val db by lazy { CitiesDatabaseDatasource() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("qwerty activity on create")
        setContentView(R.layout.activity_cities)

        val recyclerView: RecyclerView = findViewById(R.id.cityList)
        val addCity: FloatingActionButton = findViewById(R.id.dialog_button)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CitiesAdapter(
            items = db.getAllCities().toMutableList(),
            onCityClick = ::navigateToWeatherScreen
        )

        recyclerView.adapter = adapter

        addCity.setOnClickListener { showCityDialog() }
    }

    private fun showCityDialog() {
        val cityDialog = CityDialog()
        cityDialog.show(supportFragmentManager, CityDialog::class.qualifiedName)
    }

    private fun navigateToWeatherScreen(city: City) {
        val intent = Intent(this, WeatherActivity::class.java)
        intent.putExtra(CITY_KEY, city)
        startActivity(intent)
    }

    override fun onCityAdd(latitude: Double, longitude: Double) {
        val newCity = City(
            latitude = latitude,
            longitude = longitude,
            name = ""
        )
        loadWeather(newCity)

    }

    private fun loadWeather(city: City) {
        loadWeather(
            city = city,
            onSuccess = {
                city.name = it.city
                addParamsToNewCity(city)
                addCityToDb(city)
            },
            onFailure = {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun addParamsToNewCity(city: City) {

        val adapter = adapter ?: return

        adapter.items += city
        //adapter.notifyDataSetChanged() // Обновляем всё адаптер
        adapter.notifyItemInserted(adapter.itemCount - 1) // Говорим, что добавили элемент в конец
    }

    private fun addCityToDb(city: City) {
        db.addCity(city)
    }

    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}