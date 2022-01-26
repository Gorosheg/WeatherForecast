package com.first.weatherScreen.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.first.common.CITY_KEY
import com.first.common.model.City
import com.first.common.model.Weather
import com.first.common.util.showToast
import com.first.weatherScreen.R
import com.first.weatherScreen.dI.WeatherDi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

/**
 * https://www.figma.com/file/TSTEoXB2ojyMxQLdnX9fLa/Weather-Mobile-App-Design-(Community)?node-id=11%3A436
 */
class WeatherActivity : AppCompatActivity() {

    private val swipeRefresh: SwipeRefreshLayout by lazy {
        findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
    }
    private lateinit var city: City
    private var disposable = CompositeDisposable()

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this, WeatherDi.instance.getViewModelFactory(city))
            .get(WeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        city = intent.getSerializableExtra(CITY_KEY) as City
        loadWeather()

        swipeRefresh.setOnRefreshListener { loadWeather() }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun loadWeather() {
        if (isNetworkConnected()) {
            disposable += viewModel.loadWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    handleWeatherResponse(it)
                    swipeRefresh.isRefreshing = false
                }
                .doOnError(::makeToast)
                .subscribe()
        } else {
            viewModel.getSavingData()?.let(::handleWeatherResponse)
        }
    }

    private fun makeToast(throwable: Throwable) {
        showToast(throwable.message.toString())
    }

    @SuppressLint("SetTextI18n")
    private fun handleWeatherResponse(weather: Weather) {
        val town: TextView = findViewById(R.id.town)
        val degrees: TextView = findViewById(R.id.degrees)
        val dayOfWeek: TextView = findViewById(R.id.dayOfWeek)
        val date: TextView = findViewById(R.id.date)
        val skyCondition: TextView = findViewById(R.id.skyCondition)
        val skyImage: ImageView = findViewById(R.id.skyImage)
        val feelDegrees: TextView = findViewById(R.id.feelDegrees)
        val humidityParam: TextView = findViewById(R.id.humidityParam)
        val pressureParam: TextView = findViewById(R.id.pressureParam)
        val windSpeed: TextView = findViewById(R.id.windSpeed)

        town.text = weather.cityName
        degrees.text = weather.degree.toString() + "°"
        dayOfWeek.text = weather.currentDay
        date.text = weather.currentDate

        feelDegrees.text = weather.feelsLike.toString() + "°"
        humidityParam.text = weather.humidity.toString() + "%"
        pressureParam.text = weather.pressure.toString()
        windSpeed.text = weather.windSpeed.toString()
        skyCondition.setText(weather.skyCondition.text)
        skyImage.setImageResource(weather.skyImage.image)
    }
}