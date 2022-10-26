package com.example.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.app.Service
import android.content.Context
import com.example.sunnyweather.logic.network.ServiceCreator
import com.example.sunnyweather.logic.network.WeatherService
import retrofit2.await

class SunnyWeatherApplication :Application() {

    companion object{
        const val TOKEN="piLByjZ24WgxbcdW"
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate(){
        super.onCreate()
        context=applicationContext

    }

}