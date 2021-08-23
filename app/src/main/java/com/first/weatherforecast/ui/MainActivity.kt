package com.first.weatherforecast.ui

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
                val degrees: TextView = findViewById(R.id.degrees)
                val skyCondition: TextView = findViewById(R.id.skyCondition)
                val skyImage: ImageView = findViewById(R.id.SkyImage)

                degrees.text = weather.degree.toString()
                skyCondition.setText(weather.skyCondition.text)
                skyImage.setImageResource(weather.skyImage.image)
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        }

        NetworkManager.api()
            ?.getWeather()
            ?.enqueue(request)
    }
}