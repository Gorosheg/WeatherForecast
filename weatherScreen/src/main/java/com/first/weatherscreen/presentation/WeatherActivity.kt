package com.first.weatherscreen.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.first.common.CITY_KEY
import com.first.common.model.City
import com.first.network.model.WeatherResponse
import com.first.weatherscreen.R
import com.first.weatherscreen.dI.WeatherDi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class WeatherActivity : AppCompatActivity() {
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var city: City
    private var disposable = CompositeDisposable()
    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this, WeatherDi.instance.getViewModelFactory(city))
            .get(WeatherViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        city = intent.getSerializableExtra(CITY_KEY) as City
        loadWeather()

        swipeRefresh = findViewById(R.id.swipeRefreshLayout)
        swipeRefresh.setOnRefreshListener {
            loadWeather()

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun loadWeather() {
        disposable += viewModel.loadWeather()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                handleWeatherResponse(it)
                swipeRefresh.isRefreshing = false
            }
            .doOnError(::makeToast)
            .subscribe()
    }

    private fun makeToast(it: Throwable) {
        Toast.makeText(this@WeatherActivity, it.message, Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    private fun handleWeatherResponse(weather: WeatherResponse) {
        val town: TextView = findViewById(R.id.town)
        val degrees: TextView = findViewById(R.id.degrees)
        val maxMin: TextView = findViewById(R.id.minMax)
        val skyCondition: TextView = findViewById(R.id.skyCondition)
        val skyImage: ImageView = findViewById(R.id.SkyImage)
        val feelDegrees: TextView = findViewById(R.id.feelDegrees)
        val humidityParam: TextView = findViewById(R.id.humidityParam)
        val pressureParam: TextView = findViewById(R.id.pressureParam)

        town.text = weather.city

        degrees.text = weather.degree.toString() + "°C"
        maxMin.text = "${weather.tempMax}/${weather.tempMin}"

        feelDegrees.text = weather.feelsLike.toString() + "°C"
        humidityParam.text = weather.humidity.toString() + "%"
        pressureParam.text = weather.pressure.toString()
        skyCondition.setText(weather.skyCondition.text)

        Glide // Добавление изображения из интернета
            .with(this) // context
            .load(weather.skyImage.image) // Ссылка на изображение
            .into(skyImage) // View

        skyImage.clipToOutline = true
    }
}