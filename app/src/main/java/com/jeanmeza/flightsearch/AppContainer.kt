package com.jeanmeza.flightsearch

import android.content.Context
import com.jeanmeza.flightsearch.model.airport.AirportRepository
import com.jeanmeza.flightsearch.model.airport.OfflineAirportRepository
import com.jeanmeza.flightsearch.model.favorite.FavoriteRepository
import com.jeanmeza.flightsearch.model.favorite.OfflineFavoriteRepository

interface AppContainer {
    val airportRepository: AirportRepository
    val favoriteRepository: FavoriteRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val airportRepository: AirportRepository by lazy {
        OfflineAirportRepository(FlightSearchDatabase.getDatabase(context).airportDao())
    }

    override val favoriteRepository: FavoriteRepository by lazy {
        OfflineFavoriteRepository(FlightSearchDatabase.getDatabase(context).favoriteDao())
    }

}