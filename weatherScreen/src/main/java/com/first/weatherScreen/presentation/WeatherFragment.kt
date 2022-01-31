package com.first.weatherScreen.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
class WeatherFragment : Fragment(R.layout.activity_weather) {

    private val rootView by lazy { requireNotNull(view) }
    private val di by lazy { WeatherDi.instance }

    private val swipeRefresh: SwipeRefreshLayout by lazy {
        rootView.findViewById(R.id.swipeRefreshLayout)
    }

    private val city: City by lazy {
        arguments?.getSerializable(CITY_KEY) as City
    }

    private var disposable = CompositeDisposable()

    private val viewModel: WeatherViewModel by lazy {
        ViewModelProvider(this, WeatherDi.instance.getViewModelFactory(city))
            .get(WeatherViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            di.weatherNavigator.back(requireActivity())
        }

        loadWeather()

        swipeRefresh.setOnRefreshListener { loadWeather() }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun loadWeather() {
        if (requireActivity().isNetworkConnected()) {
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
        val town: TextView = rootView.findViewById(R.id.town)
        val degrees: TextView = rootView.findViewById(R.id.degrees)
        val dayOfWeek: TextView = rootView.findViewById(R.id.dayOfWeek)
        val date: TextView = rootView.findViewById(R.id.date)
        val skyCondition: TextView = rootView.findViewById(R.id.skyCondition)
        val skyImage: ImageView = rootView.findViewById(R.id.skyImage)
        val feelDegrees: TextView = rootView.findViewById(R.id.feelDegrees)
        val humidityParam: TextView = rootView.findViewById(R.id.humidityParam)
        val pressureParam: TextView = rootView.findViewById(R.id.pressureParam)
        val windSpeed: TextView = rootView.findViewById(R.id.windSpeed)

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

    companion object {

        private const val CITY_KEY = "CITY_KEY"

        fun newInstance(city: City) = WeatherFragment().apply {
            arguments = Bundle().apply {
                putSerializable(CITY_KEY, city)
            }
        }
    }
}