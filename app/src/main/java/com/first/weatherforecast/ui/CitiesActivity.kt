package com.first.weatherforecast.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.first.weatherforecast.R
import com.first.weatherforecast.database.CitiesDatabaseDatasource
import com.first.weatherforecast.model.City
import com.first.weatherforecast.network.loadWeather
import com.first.weatherforecast.ui.recycler.CitiesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CitiesActivity : AppCompatActivity(), CityAddListener {
    init {
        println("qwerty activity init")

    }

    private var adapter: CitiesAdapter? = null
    private val db by lazy { CitiesDatabaseDatasource() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("qwerty activity on create")
        setContentView(R.layout.activity_cities)

        val recyclerView: RecyclerView = findViewById(R.id.cityList)
        val addCity: FloatingActionButton = findViewById(R.id.dialog_button)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listOfCities = buildList()

        if (db.allCities().size == 0) {
            listOfCities.forEach {
                db.addCity(it)
            }
        }

        adapter = CitiesAdapter(
            items = db.allCities().toMutableList(),
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
            City(latitude = 59.939999, longitude = 30.315877, name = "Санкт-Петербург"),
            City(latitude = 55.7522, longitude = 37.6156, name = "Москва"),
            City(latitude = 60.1695, longitude = 24.9354, name = "Хельсинки")
        )
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