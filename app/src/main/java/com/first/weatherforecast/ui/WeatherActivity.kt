package com.first.weatherforecast.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.first.weatherforecast.R
import com.first.weatherforecast.model.City
import com.first.weatherforecast.model.Weather
import com.first.weatherforecast.network.NetworkManager
import com.first.weatherforecast.ui.CitiesActivity.Companion.CITY_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val request = object : Callback<Weather> {

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                val weather: Weather = response.body() ?: return
                handleWeatherResponse(weather)
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Toast.makeText(this@WeatherActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        }

        NetworkManager.api()
            ?.getWeather(latitude = city.latitude, longitude = city.longitude)
            ?.enqueue(request)
    }


    @SuppressLint("SetTextI18n")
    private fun handleWeatherResponse(weather: Weather) {
        val town: TextView = findViewById(R.id.town)
        val degrees: TextView = findViewById(R.id.degrees)
        val maxMin: TextView = findViewById(R.id.minMax)

        val skyCondition: TextView = findViewById(R.id.skyCondition)
        val skyImage: ImageView = findViewById(R.id.SkyImage)

        town.text = weather.city

        degrees.text = weather.degree.toString() + "°C"
        maxMin.text = "${weather.tempMax}/${weather.tempMin}"

        skyCondition.setText(weather.skyCondition.text)
        skyImage.setImageResource(weather.skyImage.image)
        skyImage.clipToOutline = true
    }
}