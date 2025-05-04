package com.jeanmeza.flightsearch.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanmeza.flightsearch.FlightSearchApplication
import com.jeanmeza.flightsearch.ui.airport.AirportViewModel
import com.jeanmeza.flightsearch.ui.favorites.FavoritesViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SearchBarViewModel(
                airportRepository = flightSearchApplication().container.airportRepository,
            )
        }
        initializer {
            FavoritesViewModel(
                favoriteRepository = flightSearchApplication().container.favoriteRepository,
            )
        }
        initializer {
            AirportViewModel(
                this.createSavedStateHandle(),
                flightSearchApplication().container.airportRepository,
            )
        }
    }
}

fun CreationExtras.flightSearchApplication(): FlightSearchApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)