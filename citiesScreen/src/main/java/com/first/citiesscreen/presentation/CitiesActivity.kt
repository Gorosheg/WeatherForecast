package com.first.citiesscreen.presentation

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.first.citiesscreen.R
import com.first.citiesscreen.dI.CitiesDi
import com.first.citiesscreen.presentation.recycler.CitiesAdapter
import com.first.citiesscreen.presentation.recycler.CitiesItemTouchHelper
import com.first.common.model.City
import com.first.common.model.Coordinates
import com.first.common.util.showToast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class CitiesActivity : AppCompatActivity(), CityAddListener, OnRequestPermissionsResultCallback {

    private val swipeRefresh: SwipeRefreshLayout by lazy { findViewById(R.id.citiesRefresh) }
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.cityList) }
    private val addCityActionButton: FloatingActionButton by lazy { findViewById(R.id.add_action_button) }
    private val addCityButton: Button by lazy { findViewById(R.id.dialog_button) }
    private val noCitiesImage: ImageView by lazy { findViewById(R.id.no_cities_image) }
    private val noCitiesText: TextView by lazy { findViewById(R.id.no_cities_text) }
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

    private var disposable = CompositeDisposable()

    private val adapter: CitiesAdapter by lazy {
        CitiesAdapter(
            onCityClick = ::navigateToWeatherScreen,
            removeCity = viewModel::removeCity
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
        swipeRefresh.isEnabled = false

        subscribeOnViewModel()

        if (viewModel.isFirstLaunch) {
            firstLaunchToast()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        loaderChange(userLocation.enableMyLocation(this))
        addCityActionButton.setOnClickListener { showCityDialog() }
        addCityButton.setOnClickListener { showCityDialog() }

        val itemTouchHelper = ItemTouchHelper(CitiesItemTouchHelper(::removeCity))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
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

    override fun onCityAdd(city: City) {
        loadWeatherByCity(city)
    }

    private fun loadWeatherByLocation(location: Location) {
        val city = City(coordinates = Coordinates(location.latitude, location.longitude))
        loadWeatherByCity(city)
        loaderChange(false)
    }

    private fun loaderChange(it: Boolean) {
        swipeRefresh.isRefreshing = it
    }

    private fun subscribeOnViewModel() {
        disposable += viewModel.cities
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cities ->
                adapter.items = cities
                viewModel.checkIsEmpty()
            }

        disposable += viewModel.error
            .subscribe(::makeToast)

        disposable += viewModel.isNoItems
            .subscribe(::noCities)
    }

    private fun noCities(isEmpty: Boolean) {
        addCityActionButton.isGone = isEmpty
        addCityButton.isVisible = isEmpty
        noCitiesImage.isVisible = isEmpty
        noCitiesText.isVisible = isEmpty
    }

    private fun firstLaunchToast() {
        showToast(R.string.first_launch.toString())
    }

    private fun navigateToWeatherScreen(city: City) {
        di.navigator.navigateToWeatherScreen(this, city)
    }

    private fun showCityDialog() {
        CityDialog().show(supportFragmentManager, CityDialog::class.qualifiedName)
    }

    private fun removeCity(position: Int) {
        val removingCity = adapter.items[position]
        viewModel.removeCity(removingCity)
    }

    private fun loadWeatherByCity(city: City) {
        viewModel.loadWeather(city)
    }

    private fun makeToast(throwable: UiCityExceptions) {
        when (throwable) {
            UiCityExceptions.NotFound -> {
                showToast(R.string.city_not_found.toString())
            }

            UiCityExceptions.Unknown -> {
                showToast(R.string.unknown_error.toString())
            }
        }
    }

    companion object {

        const val LOCATION_PERMISSION_REQUEST_CODE = 1

    }

}