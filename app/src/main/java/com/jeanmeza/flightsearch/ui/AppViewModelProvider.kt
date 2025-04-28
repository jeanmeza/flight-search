package com.jeanmeza.flightsearch.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanmeza.flightsearch.FlightSearchApplication
import com.jeanmeza.flightsearch.ui.favourites.FavouritesViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            AppViewModel(
                airportRepository = flightSearchApplication().container.airportRepository,
                favouriteRepository = flightSearchApplication().container.favouriteRepository,
            )
        }
        initializer {
            FavouritesViewModel(
                favouriteRepository = flightSearchApplication().container.favouriteRepository,
            )
        }
    }
}

fun CreationExtras.flightSearchApplication(): FlightSearchApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FlightSearchApplication)