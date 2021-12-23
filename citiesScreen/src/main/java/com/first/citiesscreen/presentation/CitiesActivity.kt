package com.first.citiesscreen.presentation

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.first.citiesscreen.R.id
import com.first.citiesscreen.R.layout
import com.first.citiesscreen.dI.CitiesDi
import com.first.citiesscreen.presentation.recycler.CitiesAdapter
import com.first.common.model.City
import com.first.common.model.Coordinates
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class CitiesActivity : AppCompatActivity(), CityAddListener, OnRequestPermissionsResultCallback {

    private val swipeRefresh: SwipeRefreshLayout by lazy { findViewById(id.citiesRefresh) }
    private val recyclerView: RecyclerView by lazy { findViewById(id.cityList) }
    private val addCityActionButton: FloatingActionButton by lazy { findViewById(id.add_action_button) }
    private val addCityButton: Button by lazy { findViewById(id.dialog_button) }
    private val noCitiesImage: ImageView by lazy { findViewById(id.no_cities_image) }
    private val noCitiesText: TextView by lazy { findViewById(id.no_cities_text) }
    private val di by lazy { CitiesDi.instance }

    // получим доступ к провайдеру, который хранит все ViewModel для этого Activity.
    // Методом get запрашиваем у этого провайдера конкретную модель по имени класса
    private val viewModel: CitiesViewModel by lazy {
        ViewModelProvider(this, di.getViewModelFactory())
            .get(CitiesViewModel::class.java)
    }

    private val locationManager: LocationManager by lazy {
        getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private val userLocation: UserLocation by lazy {
        UserLocation(locationManager, ::loaderChange, ::loadWeatherByLocation)
    }

    private var adapter: CitiesAdapter? = null
    private var disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_cities)
        swipeRefresh.isEnabled = false

        disposable += viewModel.cities
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                updateCitiesToList(it)
                viewModel.isEmpty()
            }

        disposable += viewModel.error
            .subscribe(::makeToast)

        disposable += viewModel.isEmpty
            .subscribe(::noCities)

        if (viewModel.isFirstLaunch() == true) {
            firstLaunchToast()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = CitiesAdapter(
            items = mutableListOf(),
            onCityClick = ::navigateToWeatherScreen,
            removeCity = ::onTrashClick
        )

        recyclerView.adapter = adapter
        loaderChange(userLocation.enableMyLocation(this))
        addCityActionButton.setOnClickListener { showCityDialog() }
        addCityButton.setOnClickListener { showCityDialog() }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun noCities(empty: Boolean) {
        if (empty) {
            addCityActionButton.isGone = true
            addCityButton.isVisible = true
            noCitiesImage.isVisible = true
            noCitiesText.isVisible = true
        } else {
            addCityActionButton.isVisible = true
            addCityButton.isGone = true
            noCitiesImage.isGone = true
            noCitiesText.isGone = true
        }
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            return
        }
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loaderChange(userLocation.enableMyLocation(this))
        } else {
            loaderChange(false)
        }
    }

    private fun showCityDialog() {
        val cityDialog = CityDialog()
        cityDialog.show(supportFragmentManager, CityDialog::class.qualifiedName)
    }

    private fun navigateToWeatherScreen(city: City) {
        di.navigator.navigateToWeatherScreen(this, city)
    }

    private fun onTrashClick(city: City) {
        viewModel.removeCity(city)
    }

    override fun onCityAdd(latitude: Double?, longitude: Double?, name: String?) {
        if (name != null) {
            val newCity = City(
                name = name
            )
            loadWeatherByCity(newCity)
        } else if (latitude != null && longitude != null) {
            val newCity = City(
                coordinates = Coordinates(latitude, longitude)
            )
            loadWeatherByCity(newCity)
        }
    }

    private fun loadWeatherByLocation(location: Location) {
        loadWeatherByCity(
            City(coordinates = Coordinates(location.latitude, location.longitude))
        )
        loaderChange(false)
    }

    private fun loadWeatherByCity(city: City) {
        viewModel.loadWeather(city)
    }

    private fun loaderChange(it: Boolean) {
        swipeRefresh.isRefreshing = it
    }

    private fun makeToast(throwable: UiCityExceptions) {
        when (throwable) {
            UiCityExceptions.NotFound -> Toast.makeText(
                this,
                "City is not found",
                Toast.LENGTH_LONG
            ).show()
            UiCityExceptions.Unknown -> Toast.makeText(
                this,
                "Something went wrong",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun firstLaunchToast() {
        Toast.makeText(this, "It is first time you open the application", Toast.LENGTH_LONG).show()
    }

    companion object {

        const val LOCATION_PERMISSION_REQUEST_CODE = 1

    }

}