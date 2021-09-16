package com.first.weatherforecast.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.first.weatherforecast.R
import com.first.weatherforecast.model.City
import com.first.weatherforecast.model.Weather
import com.first.weatherforecast.network.loadWeather
import com.first.weatherforecast.ui.CitiesActivity.Companion.CITY_KEY

class WeatherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val city = intent.getSerializableExtra(CITY_KEY) as City
        loadWeather(city)
    }

    private fun loadWeather(city: City) {
        loadWeather(
            city = city,
            onSuccess = ::handleWeatherResponse, // Передача в аргумент ссылки на функцию, т.к. аргументы совпадают
            //onSuccess = { handleWeatherResponse(it) }, // Вызов функции, без ссылки на фнкцию
            onFailure = {
                Toast.makeText(this@WeatherActivity, it.message, Toast.LENGTH_SHORT).show()
            }
        )
    }


    @SuppressLint("SetTextI18n")
    private fun handleWeatherResponse(weather: Weather) {
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
        skyImage.setImageResource(weather.skyImage.image)
        skyImage.clipToOutline = true
    }
}