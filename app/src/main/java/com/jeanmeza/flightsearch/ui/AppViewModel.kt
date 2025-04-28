package com.jeanmeza.flightsearch.ui

import androidx.lifecycle.ViewModel
import com.jeanmeza.flightsearch.model.airport.AirportRepository
import com.jeanmeza.flightsearch.model.favourite.Favourite
import com.jeanmeza.flightsearch.model.favourite.FavouriteRepository

class AppViewModel(
    private val airportRepository: AirportRepository,
    private val favouriteRepository: FavouriteRepository,
) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class FavouritesUiState(
    val favouritesList: List<Favourite> = emptyList()
)
