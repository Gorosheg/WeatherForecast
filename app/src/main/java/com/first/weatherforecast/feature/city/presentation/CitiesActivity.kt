package com.first.weatherforecast.feature.city.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.first.weatherforecast.R
import com.first.weatherforecast.common.model.City
import com.first.weatherforecast.feature.city.presentation.recycler.CitiesAdapter
import com.first.weatherforecast.feature.weather.presentation.WeatherActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers


class CitiesActivity : AppCompatActivity(), CityAddListener {

    private var adapter: CitiesAdapter? = null

    // получим доступ к провайдеру, который хранит все ViewModel для этого Activity.
    // Методом get запрашиваем у этого провайдера конкретную модель по имени класса
    private val viewModel: CitiesViewModel by lazy { ViewModelProvider(this).get(CitiesViewModel::class.java) }

    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)

        disposable += viewModel.cities
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext(::updateCitiesToList)
            .doOnError(::makeToast)
            .subscribe()

        disposable += viewModel.error
            .subscribe(::makeToast)

        val recyclerView: RecyclerView = findViewById(R.id.cityList)
        val addCity: FloatingActionButton = findViewById(R.id.dialog_button)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CitiesAdapter(
            items = mutableListOf(),
            onCityClick = ::navigateToWeatherScreen,
            removeCity = ::onTrashClick
        )

        recyclerView.adapter = adapter

        addCity.setOnClickListener { showCityDialog() }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
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

    private fun onTrashClick(city: City) {
        viewModel.removeCity(city)

        /*val adapter = adapter ?: return
        val newItems = adapter.items - city
        updateCitiesToList(newItems)*/
    }

    override fun onCityAdd(latitude: Double?, longitude: Double?, name: String?) {
        if (name != null) {
            val newCity = City(
                name = name
            )
            loadWeather(newCity)
        } else if (latitude != null && longitude != null) {
            val newCity = City(
                latitude = latitude,
                longitude = longitude
            )
            loadWeather(newCity)
        }
    }

    private fun loadWeather(city: City) {
        viewModel.loadWeather(city)
    }

    private fun updateCitiesToList(cities: List<City>) {
        val adapter = adapter ?: return
        //    val previousItemCount = adapter.itemCount
        adapter.items.clear()
        adapter.items += cities
        adapter.notifyDataSetChanged()
        //      adapter.notifyItemRangeRemoved(0, previousItemCount)
        //      adapter.notifyItemRangeInserted(previousItemCount - 1, adapter.itemCount - previousItemCount)
    }

    private fun makeToast(throwable: Throwable) {
        Toast.makeText(this, "$throwable", Toast.LENGTH_SHORT).show()
    }

    companion object {

        const val CITY_KEY = "CITY_KEY"

    }

}