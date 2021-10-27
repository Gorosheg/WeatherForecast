package com.first.weatherforecast.feature.cities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.first.weatherforecast.R
import com.first.weatherforecast.feature.weather.WeatherActivity
import com.first.weatherforecast.model.City
import com.first.weatherforecast.feature.cities.recycler.CitiesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CitiesActivity : AppCompatActivity(), CityAddListener {

    private var adapter: CitiesAdapter? = null

    // получим доступ к провайдеру, который хранит все ViewModel для этого Activity.
    // Методом get запрашиваем у этого провайдера конкретную модель по имени класса
    private val viewModel: CitiesViewModel by lazy { ViewModelProvider(this).get(CitiesViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        viewModel.data.observe(this, ::updateCitiesToList)
        viewModel.loadData()
        viewModel.toast.observe(this, ::makeToast)
        val recyclerView: RecyclerView = findViewById(R.id.cityList)
        val addCity: FloatingActionButton = findViewById(R.id.dialog_button)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CitiesAdapter(
            items = mutableListOf(),
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
        viewModel.loadWeather(city)
    }

    private fun updateCitiesToList(cities: List<City>) {
        val adapter = adapter ?: return
        val previousItemCount = adapter.itemCount
        adapter.items.clear()
        adapter.items += cities
        adapter.notifyDataSetChanged()
        //      adapter.notifyItemRangeRemoved(0, previousItemCount)
        //      adapter.notifyItemRangeInserted(previousItemCount - 1, adapter.itemCount - previousItemCount)
    }

    private fun makeToast(throwable: Throwable) {
        Toast.makeText(this, "Can't load the City", Toast.LENGTH_SHORT).show()
    }

    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}