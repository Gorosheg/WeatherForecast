package com.first.weatherforecast.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.first.weatherforecast.R
import com.first.weatherforecast.Weather
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
                degrees.text = weather.degree.toString()
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