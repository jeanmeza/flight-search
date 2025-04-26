package com.jeanmeza.flightsearch

import android.content.Context
import com.jeanmeza.flightsearch.model.airport.AirportRepository
import com.jeanmeza.flightsearch.model.airport.OfflineAirportRepository
import com.jeanmeza.flightsearch.model.favourite.FavouriteRepository
import com.jeanmeza.flightsearch.model.favourite.OfflineFavouriteRepository

interface AppContainer {
    val airportRepository: AirportRepository
    val favouriteRepository: FavouriteRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val airportRepository: AirportRepository by lazy {
        OfflineAirportRepository(FlightSearchDatabase.getDatabase(context).airportDao())
    }

    override val favouriteRepository: FavouriteRepository by lazy {
        OfflineFavouriteRepository(FlightSearchDatabase.getDatabase(context).favouriteDao())
    }

}