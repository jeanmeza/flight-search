package com.jeanmeza.flightsearch.ui.airport

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanmeza.flightsearch.model.airport.Airport
import com.jeanmeza.flightsearch.model.airport.AirportRepository
import com.jeanmeza.flightsearch.model.favorite.Favorite
import com.jeanmeza.flightsearch.model.favorite.FavoriteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AirportViewModel(
    savedStateHandle: SavedStateHandle,
    private val airportRepository: AirportRepository,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val airportId: Int = checkNotNull(savedStateHandle[AirportDestination.AIRPORT_ID_ARG])
    private val airportFlow = airportRepository.getAirportStream(airportId)
    private val relatedAirportsFlow = airportRepository.getAllAirportsExcept(airportId)
    private val favoriteFlow = favoriteRepository.getAllFavoritesStream()

    val uiState: StateFlow<AirportUiState> =
        combine(
            airportFlow,
            relatedAirportsFlow,
            favoriteFlow
        ) { airport, destinations, favorites ->
            val departureCode = airport?.iataCode
            val destWithFavorites = destinations.shuffled().take(5).map { destination ->
                val favorite = favorites.find { favorite ->
                    favorite.departureCode == departureCode
                            && favorite.destinationCode == destination.iataCode
                }
                AirportWithFavorite(destination, favorite)
            }
            AirportUiState(
                airport = airport,
                destinations = destWithFavorites
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = AirportUiState(),
        )

    suspend fun favoriteItem(iata_dep: String, iata_dest: String) {
        favoriteRepository.insertFavourite(
            Favorite(
                departureCode = iata_dep,
                destinationCode = iata_dest
            )
        )
    }

    suspend fun removeFavorite(favorite: Favorite) {
        favoriteRepository.deleteFavourite(favorite)
    }
}

data class AirportUiState(
    val airport: Airport? = null,
    val destinations: List<AirportWithFavorite> = emptyList()
)

data class AirportWithFavorite(
    val airport: Airport,
    val favorite: Favorite?,
)