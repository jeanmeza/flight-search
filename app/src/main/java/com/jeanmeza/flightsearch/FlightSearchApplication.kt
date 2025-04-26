package com.jeanmeza.flightsearch

import android.app.Application

class FlightSearchApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}