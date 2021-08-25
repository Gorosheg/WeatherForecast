package com.first.weatherforecast.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.first.weatherforecast.R
import com.first.weatherforecast.model.Weather
import com.first.weatherforecast.network.NetworkManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        loadWeather()
    }

    private fun loadWeather() {
        val request = object : Callback<Weather> {

            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                val weather: Weather = response.body() ?: return
                handleWeatherResponse(weather)
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        }

        NetworkManager.api()
            ?.getWeather()
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
    }
}